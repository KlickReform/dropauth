package de.klickreform.dropauth.oauth2.scope;

import de.klickreform.dropauth.OAuthSettings;
import net.minidev.json.JSONArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class ScopeSetTest {

    @Test
    public void construct() {
        ScopeSet scopeSet = new ScopeSet("scope1","scope2","scope3");
        assertTrue(scopeSet.size() == 3);
        assertTrue(scopeSet.contains("scope1"));
    }

    @Test
    public void parseFromString() {
        ScopeSet scopeSet = ScopeSet.parse("scope1"+ OAuthSettings.SCOPE_DELIMITER + "scope2");
        assertTrue(scopeSet.size() == 2);
        //assertTrue(scopeSet.contains("scope1"));
    }

    @Test
    public void parseFromCollection() {
        Collection<String> scopeList = new ArrayList<String>();
        scopeList.add("scope1");
        scopeList.add("scope2");
        scopeList.add("scope3");
        ScopeSet scopeSet = ScopeSet.parse(scopeList);
        assertTrue(scopeSet.size() == 3);
        assertTrue(scopeSet.contains("scope1"));
    }

    @Test
    public void parseFromJSONArray() {
        JSONArray scopeArray = new JSONArray();
        scopeArray.add("scope1");
        scopeArray.add("scope2");
        scopeArray.add("scope3");
        ScopeSet scopeSet = ScopeSet.parse(scopeArray);
        assertTrue(scopeSet.size() == 3);
        assertTrue(scopeSet.contains("scope1"));
    }

}