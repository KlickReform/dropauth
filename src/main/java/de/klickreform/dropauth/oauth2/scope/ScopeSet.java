package de.klickreform.dropauth.oauth2.scope;

import de.klickreform.dropauth.OAuthSettings;
import net.minidev.json.JSONArray;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

/**
 * Set of Scopes based on a LinkedHashSet to provide various utility operations
 * when working with multiple scopes.
 *
 * @author Benjamin Bestmann
 */
public class ScopeSet extends LinkedHashSet<String> {

    /**
     * Crates a new ScopeSet with the given String values.
     *
     * @param scopes The scope values.
     */
    public ScopeSet(final String ... scopes) {
        for(String scope : scopes) {
            add(scope);
        }
    }

    /**
     * Returns a string representation of the ScopeSet.
     * The different Scope Values a separated by a space.
     *
     * @return The String list of all Scopes contained in the ScopeSet
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(String scope : this) {
            if(sb.length() > 0) {
                sb.append(OAuthSettings.SCOPE_DELIMITER);
            }
            sb.append(scope);
        }
        return sb.toString();
    }

    /**
     * Parses a ScopeSet from a String representation of Scopes.
     *
     * @param scopes The String containing the Scopes.
     * @return The ScopeSet created from the given String.
     */
    public static ScopeSet parse(final String scopes) {
        if(scopes == null) {
            return null;
        }
        ScopeSet scopeSet = new ScopeSet();
        if(scopes.trim().isEmpty()) {
            return scopeSet;
        }
        StringTokenizer st = new StringTokenizer(scopes, OAuthSettings.SCOPE_DELIMITER);
        while(st.hasMoreElements()) {
            scopeSet.add(st.nextToken());
        }
        return scopeSet;
    }

    /**
     * Parses a new ScopeSet from the provided Collection.
     *
     * @param scopes The Collection of String Scopes.
     * @return The parsed ScopeSet containing the provided Scopes.
     */
    public static ScopeSet parse(final Collection<String> scopes) {
        if(scopes == null ) {
            return null;
        }
        ScopeSet scopeSet = new ScopeSet();
        for(String scope : scopes) {
            scopeSet.add(scope);
        }
        return scopeSet;
    }

    /**
     * Parses a new ScopeSet from the provided JSONArray
     *
     * @param scopeArray The JSONArray containing the Scopes
     * @return The parsed ScopeSet containing the provided Scopes
     */
    public static ScopeSet parse(final JSONArray scopeArray) {
        if(scopeArray == null) {
            return null;
        }
        ScopeSet scopeSet = new ScopeSet();
        for(Object entry : scopeArray) {
            scopeSet.add((String)entry);
        }
        return scopeSet;
    }

}
