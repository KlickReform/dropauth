package de.klickreform.dropauth.oauth2.token;

import de.klickreform.dropauth.oauth2.client.SimpleOAuthClient;
import de.klickreform.dropauth.oauth2.owner.ResourceOwner;
import de.klickreform.dropauth.oauth2.scope.ScopeSet;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SimpleTokenServiceTest {

    private static SimpleTokenService simpleTokenService;
    private static ResourceOwner resourceOwner;

    @BeforeClass
    public static void runBeforeTests() {
        simpleTokenService = new SimpleTokenService(3600);
        resourceOwner = new ResourceOwner() {
            @Override
            public String getPassword() {
                return "mysecret";
            }

            @Override
            public String getIdentifier() {
                return "ownerid";
            }
        };
    }

    @Test
    public void createRefreshAndResolveAccessToken() {
        // Mock Client
        SimpleOAuthClient client = new SimpleOAuthClient();
        client.setId("client");
        // Mock ScopeSet
        ScopeSet scopeSet = ScopeSet.parse("scope1 scope2");
        // Issue a new AccessToken
        AccessToken issuedToken = this.simpleTokenService.createAccessToken(client,resourceOwner,scopeSet);
        // Resolve the issued AccessToken and check if it's the same data
        AccessToken resolvedToken = simpleTokenService.resolveToken(issuedToken.getToken());
        assertTrue(resolvedToken.getOwner().equals(resourceOwner.getIdentifier()));
        assertTrue(resolvedToken.getExpiresIn() == 3600);
        // Refresh the Token
        AccessToken newTokenTemp = simpleTokenService.refreshToken(client,issuedToken.getRefreshToken());
        AccessToken newToken = simpleTokenService.resolveToken(newTokenTemp.getToken());
        assertTrue(newToken.getOwner().equals(resourceOwner.getIdentifier()));
        assertTrue(newToken.getRefreshToken().equals(issuedToken.getRefreshToken()));
    }

}