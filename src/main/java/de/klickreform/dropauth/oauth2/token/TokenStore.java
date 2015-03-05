package de.klickreform.dropauth.oauth2.token;

import de.klickreform.dropkit.exception.UnauthorizedException;

/**
 * Interface for TokenStore implementations.
 *
 * @author Benjamin Bestmann
 */
public interface TokenStore {

    public void storeToken(AccessToken token);

    public AccessToken resolveToken(String tokenValue);

    public AccessToken resolveRefreshToken(String refreshToken);

    public void removeToken(String token) throws UnauthorizedException;

}
