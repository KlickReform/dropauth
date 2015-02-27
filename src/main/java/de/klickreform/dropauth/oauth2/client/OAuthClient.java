package de.klickreform.dropauth.oauth2.client;

import de.klickreform.dropauth.oauth2.owner.Principal;

/**
 * Interface for registered OAuthClient Applications.
 *
 * @author Benjamin Bestmann
 */
public interface OAuthClient extends Principal {

    public String getSecret();

    public String getName();

    public String getDescription();

    public String getWebsite();

    public String getLogoUrl();

}
