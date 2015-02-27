package de.klickreform.dropauth.oauth2.response;

import java.io.Serializable;

/**
 * Represent responses to AccessTokenRequests containing the token string as
 * well as additional information on the token.
 *
 * @author Benjamin Bestmann
 */
public class AccessTokenResponse implements Serializable {

    private String accessToken;
    private String tokenType;
    private int expiresIn;
    private String refreshToken;

    public AccessTokenResponse() { }

    public AccessTokenResponse(String accessToken, String tokenType, String state) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
