package de.klickreform.dropauth.oauth2.scope;

import de.klickreform.dropauth.exceptions.InvalidScopeException;

import java.util.Map;

/**
 * Interface for the implementation of ScopeService instances which can be used
 * to process Scopes.
 *
 * @author Benjamin Bestmann
 */
public interface ScopeService {

    public ScopeSet parseValidScopes(String scopeString) throws InvalidScopeException;

    public Map<String,Scope> getAllValidScopes();

}
