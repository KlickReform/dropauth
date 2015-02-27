package de.klickreform.dropauth.oauth2.token;

import de.klickreform.dropauth.oauth2.owner.ResourceOwner;

/**
 * Interface for TokenService implementations.
 *
 * @author Benjamin Bestmann
 */
public interface TokenService {

    public AccessToken createAccessToken(ResourceOwner resourceOwner);

    public AccessToken resolveToken(String token);

}
