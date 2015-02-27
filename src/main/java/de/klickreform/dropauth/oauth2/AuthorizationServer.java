package de.klickreform.dropauth.oauth2;

import de.klickreform.dropauth.exceptions.InvalidScopeException;
import de.klickreform.dropauth.oauth2.client.OAuthClientService;
import de.klickreform.dropauth.oauth2.owner.ResourceOwnerAuthProvider;
import de.klickreform.dropauth.exceptions.InvalidGrantTypeException;
import de.klickreform.dropauth.exceptions.MissingRequiredArgumentException;
import de.klickreform.dropauth.oauth2.client.OAuthClient;
import de.klickreform.dropauth.oauth2.owner.ResourceOwner;
import de.klickreform.dropauth.oauth2.scope.ScopeService;
import de.klickreform.dropauth.oauth2.scope.ScopeSet;
import de.klickreform.dropauth.oauth2.request.AccessTokenRequest;
import de.klickreform.dropauth.oauth2.response.AccessTokenResponse;
import de.klickreform.dropauth.oauth2.token.AccessToken;
import de.klickreform.dropauth.oauth2.token.TokenService;
import de.klickreform.dropkit.exception.NotFoundException;
import de.klickreform.dropkit.exception.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Central Class that will execute the various OAuth2 requests.
 *
 * @author Benjamin Bestmann
 */
public class AuthorizationServer {

    private ResourceOwnerAuthProvider resourceOwnerAuthProvider;
    private OAuthClientService clientService;
    private ScopeService scopeService;
    private TokenService tokenService;

    public AuthorizationServer(TokenService tokenService, ResourceOwnerAuthProvider authProvider, ScopeService scopeService, OAuthClientService clientService) {
        this.resourceOwnerAuthProvider = authProvider;
        this.scopeService = scopeService;
        this.clientService = clientService;
        this.tokenService = tokenService;
    }

    public String executeAuthorizationRequest(HttpServletRequest httpRequest) {
        // TODO: Implement Authorization Requests
        return null;
    }

    public AccessTokenResponse executeAccessTokenRequest(HttpServletRequest httpRequest, MultivaluedMap<String,String> formParams) throws MissingRequiredArgumentException, InvalidGrantTypeException, InvalidScopeException, UnauthorizedException, NotFoundException {

        // Parse the HttpServletRequest and check if it's a valid AccessTokenRequest (throws MissingRequiredArgumentException)
        AccessTokenRequest tokenRequest = new AccessTokenRequest(httpRequest,formParams);

        // Authenticate Client and ResourceOwner (throws NotFoundException)
        OAuthClient client = clientService.authenticate(tokenRequest.getClientId());
        ResourceOwner resourceOwner = resourceOwnerAuthProvider.authenticate(tokenRequest.getUsername(),tokenRequest.getPassword());

        // Parse and validate requested Scopes (throws InvalidScopeException)
        ScopeSet scopeSet = scopeService.parseValidScopes(tokenRequest.getScope());

        // Create AccessToken using the TokenService
        AccessToken token = tokenService.createAccessToken(resourceOwner);

        // Build and return Response
        AccessTokenResponse response = new AccessTokenResponse();
        response.setAccessToken(token.getToken());
        response.setExpiresIn(token.getExpiresIn());
        response.setTokenType(token.getType());
        return response;

    }

    public TokenService getTokenService() {
        return this.tokenService;
    }

}
