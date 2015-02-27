package de.klickreform.dropauth.oauth2.scope;

/**
 * Simple Interface to represent Scopes within the application.
 *
 * @author Benjamin Bestmann
 */
public interface Scope {

    public String getId();

    public String getDesignator();

    public String getDescription();

}
