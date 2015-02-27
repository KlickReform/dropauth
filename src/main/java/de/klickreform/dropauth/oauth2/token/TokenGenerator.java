package de.klickreform.dropauth.oauth2.token;

/**
 * Interface for TokenGenerator implementations, which can be used
 * to generate tokens.
 *
 * @author Benjamin Bestmann
 */
public interface TokenGenerator {

    public String generateToken();

}
