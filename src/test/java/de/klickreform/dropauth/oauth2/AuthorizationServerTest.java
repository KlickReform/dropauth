package de.klickreform.dropauth.oauth2;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import de.klickreform.dropauth.OAuthSettings;
import de.klickreform.dropauth.oauth2.client.OAuthClientService;
import de.klickreform.dropauth.oauth2.client.SimpleOAuthClient;
import de.klickreform.dropauth.oauth2.owner.ResourceOwner;
import de.klickreform.dropauth.oauth2.owner.ResourceOwnerAuthProvider;
import de.klickreform.dropauth.oauth2.response.AccessTokenResponse;
import de.klickreform.dropauth.oauth2.scope.ScopeService;
import de.klickreform.dropauth.oauth2.scope.ScopeSet;
import de.klickreform.dropauth.oauth2.token.AccessToken;
import de.klickreform.dropauth.oauth2.token.TokenService;
import org.eclipse.jetty.server.Request;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertTrue;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;

public class AuthorizationServerTest {

    @Test
    public void executeAccessTokenRequest() {
        // Mock Service Providers
        ResourceOwnerAuthProvider resourceOwnerAuthProvider = Mockito.mock(ResourceOwnerAuthProvider.class);
        OAuthClientService clientService = Mockito.mock(OAuthClientService.class);
        ScopeService scopeService = Mockito.mock(ScopeService.class);
        TokenService tokenService = Mockito.mock(TokenService.class);
        // Mock OAuthClientService
        SimpleOAuthClient client = new SimpleOAuthClient();
        client.setId("client");
        Mockito.doReturn(client).when(clientService).authenticate("client");
        // Mock ResourceOwnerAuthProvider
        ResourceOwner resourceOwner = Mockito.mock(ResourceOwner.class);
        Mockito.doReturn(resourceOwner).when(resourceOwnerAuthProvider).authenticate("owner","password");
        // Mock ScopeService
        ScopeSet scopeSet = ScopeSet.parse("scope1 scope2");
        Mockito.doReturn(scopeSet).when(scopeService).parseValidScopes("scope1 scope2");
        // Mock TokenService
        AccessToken token = new AccessToken("token","owner","refreshToken",3600);
        Mockito.doReturn(token).when(tokenService).createAccessToken(client,resourceOwner,scopeSet);
        // Fake Request
        AuthorizationServer authServer = new AuthorizationServer(tokenService,resourceOwnerAuthProvider,scopeService,clientService);
        HttpServletRequest request = Mockito.mock(Request.class);
        Mockito.doReturn("password").when(request).getParameter(OAuthSettings.Params.GRANT_TYPE);
        Mockito.doReturn("owner").when(request).getParameter(OAuthSettings.Params.USERNAME);
        Mockito.doReturn("client").when(request).getParameter(OAuthSettings.Params.CLIENT_ID);
        Mockito.doReturn("scope1 scope2").when(request).getParameter(OAuthSettings.Params.SCOPE);
        // Fake Params
        MultivaluedMap<String,String> params = new MultivaluedMapImpl();
        params.add(OAuthSettings.Params.GRANT_TYPE,"password");
        params.add(OAuthSettings.Params.USERNAME,"owner");
        params.add(OAuthSettings.Params.PASSWORD,"password");
        params.add(OAuthSettings.Params.CLIENT_ID,"client");
        params.add(OAuthSettings.Params.SCOPE,"scope1 scope2");
        // Test AccessTokenRequest
        AccessTokenResponse response = authServer.executeAccessTokenRequest(request,params);
        assertTrue(response.getAccessToken().equals("token"));
    }

}