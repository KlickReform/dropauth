package de.klickreform.dropauth.api;

import de.klickreform.dropauth.OAuthSettings;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Endpoint used to interact with the resource owner and obtain authorization grants.
 *
 * @author Benjamin Bestmann
 */
@Path("/oauth2/auth")
public class AuthorizationEndpoint {

    @GET
    public Response authorizationRequest( @Context HttpServletRequest request,
                                          @QueryParam(OAuthSettings.Params.RESPONSE_TYPE) String responseType,
                                          @QueryParam(OAuthSettings.Params.CLIENT_ID) String clientId,
                                          @QueryParam(OAuthSettings.Params.REDIRECT_URI) String redirectURI,
                                          @QueryParam(OAuthSettings.Params.SCOPE) String scope,
                                          @QueryParam(OAuthSettings.Params.STATE) String state,
                                          @QueryParam(OAuthSettings.Params.USERNAME) String username,
                                          @QueryParam(OAuthSettings.Params.PASSWORD) String password ) {

        // TODO: Implement Authorization Requests
        return Response.ok().build();
    }

}
