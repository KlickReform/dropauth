package de.klickreform.dropauth.oauth2.resource;

import de.klickreform.dropauth.oauth2.owner.ResourceOwner;
import de.klickreform.dropkit.mongo.MorphiaDomainModel;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

/**
 * A MorphiaModel that represents an OAuth2 resource.
 *
 * @author Benjamin Bestmann
 */
public class MorphiaResource<K extends ObjectId> extends MorphiaDomainModel<K> implements Resource {

    @Reference
    @Indexed
    private ResourceOwner resourceOwner;

    public ResourceOwner getResourceOwner() {
        return resourceOwner;
    }

    public void setResourceOwner(ResourceOwner resourceOwner) {
        this.resourceOwner = resourceOwner;
    }
}
