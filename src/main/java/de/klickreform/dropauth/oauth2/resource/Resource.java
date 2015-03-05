package de.klickreform.dropauth.oauth2.resource;

import de.klickreform.dropauth.oauth2.owner.ResourceOwner;

/**
 * An entity that is owned by a ResourceOwner.
 *
 * @author Benjamin Bestmann
 */
public interface Resource {

    public ResourceOwner getResourceOwner();

    public void setResourceOwner(ResourceOwner resourceOwner);

}
