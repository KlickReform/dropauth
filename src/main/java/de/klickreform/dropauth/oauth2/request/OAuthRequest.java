package de.klickreform.dropauth.oauth2.request;

import de.klickreform.dropauth.exceptions.InvalidGrantTypeException;
import de.klickreform.dropauth.exceptions.MissingRequiredArgumentException;

/**
 * Interface for OAuth2 request handling.
 *
 * @author Benjamin Bestmann
 */
public interface OAuthRequest {

    public void validate() throws MissingRequiredArgumentException, InvalidGrantTypeException;

}
