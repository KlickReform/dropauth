package de.klickreform.dropauth.oauth2.token;

import de.klickreform.dropauth.OAuthSettings;
import de.klickreform.dropauth.oauth2.response.AccessTokenResponse;

import java.io.Serializable;
import java.util.Date;

/**
 * Implementation for AccessTokens.
 *
 * @author Benjamin Bestmann
 */
public class AccessToken implements Serializable {

    private String token;
    private String client;
    private String owner;
    private String scopes;
    private String type;
    private String refreshToken;
    private int expiresIn;
    private Date expiryDate;
    private static final long serialVersionUID = 1L;

    public AccessToken() { }

    public AccessToken(String token, String owner, String refreshToken, int expiresIn) {
        this.token = token;
        this.owner = owner;
        this.refreshToken = refreshToken;
        this.type = OAuthSettings.DEFAULT_TOKEN_TYPE;
        this.expiresIn = expiresIn;
        this.expiryDate = new Date(new Date().getTime() + this.expiresIn * 1000);
    }

    public AccessToken(String token, String client, String owner, String scopes, String refreshToken, int expiresIn) {
        this.token = token;
        this.client = client;
        this.owner = owner;
        this.scopes = scopes;
        this.refreshToken = refreshToken;
        this.type = OAuthSettings.DEFAULT_TOKEN_TYPE;
        this.expiresIn = expiresIn;
        this.expiryDate = new Date(new Date().getTime() + this.expiresIn * 1000);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public AccessTokenResponse toResponse() {
        AccessTokenResponse response = new AccessTokenResponse();
        response.setAccessToken(this.getToken());
        response.setExpiresIn(this.getExpiresIn());
        response.setRefreshToken(this.getRefreshToken());
        response.setTokenType(this.getType());
        return response;
    }

}
