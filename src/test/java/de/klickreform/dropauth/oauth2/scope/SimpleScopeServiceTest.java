package de.klickreform.dropauth.oauth2.scope;

import de.klickreform.dropauth.exceptions.InvalidScopeException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SimpleScopeServiceTest {

    private static SimpleScopeService scopeService;

    @BeforeClass
    public static void setupBeforeClass() throws IOException {
        assertNotNull("Test file missing", SimpleScopeServiceTest.class.getResource("/scopes.json"));
        scopeService = new SimpleScopeService(SimpleScopeServiceTest.class.getResource("/scopes.json").getFile());
    }

    @Test
    public void scopeParsing() {
        assertTrue(scopeService.getAllValidScopes().size() == 3);
        assertTrue(scopeService.getAllValidScopes().containsKey("scope1"));
    }

    @Test
    public void parseValidScopes() throws InvalidScopeException {
        ScopeSet scopeSet = scopeService.parseValidScopes("scope1 scope2 scope3");
        assertTrue(scopeSet.size() == 3);
    }

}