package de.klickreform.dropauth.oauth2.token;

import de.klickreform.dropauth.OAuthSettings;

import java.io.Serializable;
import java.util.Date;

/**
 * Implementation for AccessTokens.
 *
 * @author Benjamin Bestmann
 */
public class AccessToken implements Serializable {

    private String token;
    private String owner;
    private String type;
    private int expiresIn;
    private Date expiryDate;
    private static final long serialVersionUID = 1L;

    public AccessToken() { }

    public AccessToken(String token, String owner, int expiresIn) {
        this.token = token;
        this.owner = owner;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

}
