package de.klickreform.dropauth.oauth2.token;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AccessTokenTest {

    @Test
    public void construct() {
        AccessToken token = new AccessToken("tokenValue", "owner", 3600);
        assertTrue(token.getOwner().equals("owner"));
        assertTrue(token.getToken().equals("tokenValue"));
        assertTrue(token.getExpiresIn() == 3600);
    }

}