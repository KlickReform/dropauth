package de.klickreform.dropauth.oauth2.owner;

/**
 * Interface for ResourceOwners, that can obtain access to an applications resources.
 * Has to be implemented by the application.
 *
 * @author Benjamin Bestmann
 */
public interface ResourceOwner extends Principal {

    public String getPassword();

}
