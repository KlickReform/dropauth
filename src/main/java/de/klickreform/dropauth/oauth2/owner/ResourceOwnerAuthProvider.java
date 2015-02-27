package de.klickreform.dropauth.oauth2.owner;

import de.klickreform.dropkit.exception.UnauthorizedException;

/**
 * Interface to identify and authenticate ResourceOwners. Has to be implemented on
 * application level.
 *
 * @author Benjamin Bestmann
 */
public interface ResourceOwnerAuthProvider {

    public ResourceOwner identify(String identifier) throws UnauthorizedException;

    public ResourceOwner authenticate(String identifier, String password) throws UnauthorizedException;

}
