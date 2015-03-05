package de.klickreform.dropauth.oauth2.request;

import de.klickreform.dropauth.OAuthSettings;
import de.klickreform.dropauth.exceptions.InvalidGrantTypeException;
import de.klickreform.dropauth.exceptions.MissingRequiredArgumentException;
import de.klickreform.dropauth.oauth2.validation.AccessTokenRequestValidator;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;

/**
 * Request Object to cast an AccessTokenRequest from a HttpServletRequest
 * coming from the ServletContainer.
 *
 * @author Benjamin Bestmann
 */
public class AccessTokenRequest extends AbstractOAuthRequest implements OAuthRequest {

    private String grantType;
    private String clientId;
    private String username;
    private String password;
    private String scope;
    private String refreshToken;
    private MultivaluedMap<String, String> formParams;

    public AccessTokenRequest(HttpServletRequest request, MultivaluedMap<String,String> formParams) throws MissingRequiredArgumentException, InvalidGrantTypeException {
        // Use constructor of AbstractOAuthRequest
        super(request);
        // Initialize RequestValidator
        this.requestValidator = new AccessTokenRequestValidator();
        this.formParams = formParams;
        // Validate Request
        this.validate();
        // If Request is valid, cast fields
        this.grantType = formParams.getFirst(OAuthSettings.Params.GRANT_TYPE);
        this.clientId = formParams.getFirst(OAuthSettings.Params.CLIENT_ID);
        this.username = formParams.getFirst(OAuthSettings.Params.USERNAME);
        this.password = formParams.getFirst(OAuthSettings.Params.PASSWORD);
        this.refreshToken = formParams.getFirst(OAuthSettings.Params.REFRESH_TOKEN);
        if(formParams.getFirst(OAuthSettings.Params.SCOPE) == null) {
            this.scope = "";
        } else {
            this.scope = formParams.getFirst(OAuthSettings.Params.SCOPE);
        }
    }

    @Override
    public void validate() throws MissingRequiredArgumentException, InvalidGrantTypeException {
        // Validate Request using the AccessTokenRequestValidator
        requestValidator.validate(this.formParams);
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
