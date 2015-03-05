package de.klickreform.dropauth.oauth2.validation;

import de.klickreform.dropauth.OAuthSettings;
import de.klickreform.dropauth.exceptions.InvalidGrantTypeException;
import de.klickreform.dropauth.exceptions.MissingRequiredArgumentException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * RequestValidator used for AccessTokenRequests addressed to the TokenEndpoint.
 *
 * @author Benjamin Bestmann
 */
public class AccessTokenRequestValidator extends AbstractRequestValidator<HttpServletRequest> implements RequestValidator<HttpServletRequest> {

    public AccessTokenRequestValidator() {
        // Grant Type is always required for token requests
        requiredParams.add(OAuthSettings.Params.GRANT_TYPE);
    }

    @Override
    public void validate(MultivaluedMap<String,String> formParams) throws MissingRequiredArgumentException, InvalidGrantTypeException {
        this.validateRequiredFormParameters(formParams);
        String grantType = extractParam(formParams, OAuthSettings.Params.GRANT_TYPE);
        if(grantType.equals(OAuthSettings.GrantTypes.AUTH_CODE)) {
            // TODO: Implement Authorization Code Grant Validation
            throw new InvalidGrantTypeException("Authorization Code Grant currently not supported.");
        } else if(grantType.equals(OAuthSettings.GrantTypes.PASSWORD)) {
            // Resource Owner Password Credentials
            requiredParams.add(OAuthSettings.Params.USERNAME);
            requiredParams.add(OAuthSettings.Params.PASSWORD);
            requiredParams.add(OAuthSettings.Params.CLIENT_ID);
        } else if(grantType.equals(OAuthSettings.GrantTypes.CLIENT_CREDENTIALS)) {
            // TODO: Implement Client Credentials Validation
            throw new InvalidGrantTypeException("Client Credentials Grant currently not supported.");
        } else if(grantType.equals(OAuthSettings.GrantTypes.REFRESH)) {
            // Refresh Token Grant
            requiredParams.add(OAuthSettings.Params.REFRESH_TOKEN);
            requiredParams.add(OAuthSettings.Params.CLIENT_ID);
        } else {
            throw new InvalidGrantTypeException("Unknown Grant Type: '" + grantType + "'");
        }
        this.validateRequiredFormParameters(formParams);
    }

    private String extractParam(MultivaluedMap<String,String> params, String name) throws MissingRequiredArgumentException {
        List<String> paramList = params.get(name);
        if(paramList.size() == 1) {
            return paramList.iterator().next();
        } else {
            throw new MissingRequiredArgumentException("Empty or invalid request parameter: " + name);
        }
    }

}
