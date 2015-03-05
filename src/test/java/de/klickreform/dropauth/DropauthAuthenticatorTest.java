package de.klickreform.dropauth;

import de.klickreform.dropauth.oauth2.owner.ResourceOwner;
import de.klickreform.dropauth.oauth2.owner.ResourceOwnerAuthProvider;
import de.klickreform.dropauth.oauth2.token.AccessToken;
import de.klickreform.dropauth.oauth2.token.SimpleTokenService;
import de.klickreform.dropauth.oauth2.token.TokenService;
import io.dropwizard.auth.AuthenticationException;
import org.junit.Test;
import org.mockito.Mockito;

public class DropauthAuthenticatorTest {

    @Test
    public void authenticate() throws AuthenticationException {
        ResourceOwnerAuthProvider authProvider = Mockito.mock(ResourceOwnerAuthProvider.class);
        TokenService tokenService = Mockito.mock(SimpleTokenService.class);
        AccessToken accessToken = new AccessToken("sometoken","owner", "refreshToken", 3600);
        Mockito.doReturn(accessToken).when(tokenService).resolveToken("sometoken");
        ResourceOwner owner = Mockito.mock(ResourceOwner.class);
        Mockito.doReturn(owner).when(authProvider).identify("owner");
        DropauthAuthenticator authenticator = new DropauthAuthenticator(authProvider,tokenService);
        authenticator.authenticate("sometoken");
    }

}