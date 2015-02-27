package de.klickreform.dropauth.oauth2.client;

/**
 * Simple implementation of the OAuthClient interface to manually register
 * clients within the application.
 *
 * @author Benjamin Bestmann
 */
public class SimpleOAuthClient implements OAuthClient {

    private String id;
    private String secret;
    private String name;
    private String description;
    private String website;
    private String logoUrl;

    public String getId() {
        return id;
    }

    @Override
    public String getIdentifier() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

}
