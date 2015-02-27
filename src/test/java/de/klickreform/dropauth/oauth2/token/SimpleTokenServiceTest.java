package de.klickreform.dropauth.oauth2.token;

import de.klickreform.dropauth.oauth2.owner.ResourceOwner;
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
    public void createAndResolveAccessToken() {
        // Issue a new AccessToken
        AccessToken issuedToken = this.simpleTokenService.createAccessToken(resourceOwner);
        // Resolve the issued AccessToken and check if it's the same data
        AccessToken resolvedToken = simpleTokenService.resolveToken(issuedToken.getToken());
        assertTrue(resolvedToken.getOwner().equals(resourceOwner.getIdentifier()));
        assertTrue(resolvedToken.getExpiresIn() == 3600);
    }

}