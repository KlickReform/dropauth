package de.klickreform.dropauth.oauth2.scope;

/**
 * Simple implementation of the Scope interface.
 *
 * @author Benjamin Bestmann
 */
public class SimpleScope  implements Scope {

    private String id;
    private String designator;
    private String description;

    public SimpleScope() { }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getDesignator() {
        return designator;
    }

    public void setDesignator(String designator) {
        this.designator = designator;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
