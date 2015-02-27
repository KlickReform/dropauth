package de.klickreform.dropauth.api;

import de.klickreform.dropauth.oauth2.AuthorizationServer;
import de.klickreform.dropauth.oauth2.response.AccessTokenResponse;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 * Jersey TokenEndpoint that can be used by a client to obtain an access token by
 * presenting its authorization grant or refresh token.
 *
 * @author Benjamin Bestmann
 */
@Path("/oauth2/token")
public class TokenEndpoint {

    private AuthorizationServer authorizationServer;

    public TokenEndpoint(AuthorizationServer authorizationServer) {
        this.authorizationServer = authorizationServer;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response accessTokenRequest( @Context HttpServletRequest request,
                                        MultivaluedMap<String, String>formParams) {
        // Respond to requests using the AuthorizationServer
        AccessTokenResponse response = authorizationServer.executeAccessTokenRequest(request, formParams);
        return Response.ok(response).build();
    }

}
