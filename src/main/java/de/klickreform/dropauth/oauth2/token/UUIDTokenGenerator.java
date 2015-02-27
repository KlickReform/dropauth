package de.klickreform.dropauth.oauth2.token;

import java.util.UUID;

/**
 * Simple implementation of the TokenGenerator interface using java.util.UUID
 * Creates random UUIDs and returns them as String values.
 *
 * @author Benjamin Bestmann
 */
public class UUIDTokenGenerator implements TokenGenerator {

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }

}
