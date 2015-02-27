package de.klickreform.dropauth.oauth2.token;

/**
 * Interface for TokenStore implementations.
 *
 * @author Benjamin Bestmann
 */
public interface TokenStore {

    public void storeToken(AccessToken token);

    public AccessToken resolveToken(String tokenValue);

}
