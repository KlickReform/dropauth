package de.klickreform.dropauth.oauth2.client;

import de.klickreform.dropkit.exception.UnauthorizedException;

/**
 * Service to handle all operations that involve OAuthCients.
 *
 * @author Benjamin Bestmann
 */
public interface OAuthClientService {

    public OAuthClient authenticate(String identifier) throws UnauthorizedException;

    public OAuthClient authenticate(String identifier, String secret) throws UnauthorizedException;

}
