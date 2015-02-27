package de.klickreform.dropauth.oauth2.token;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UUIDTokenGeneratorTest {

    @Test
    public void createToken() {
        TokenGenerator generator = new UUIDTokenGenerator();
        String token = generator.generateToken();
        assertTrue(token.length() == (36-4));
    }

}