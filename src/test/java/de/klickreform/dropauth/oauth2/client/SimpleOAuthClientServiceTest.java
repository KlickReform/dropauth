package de.klickreform.dropauth.oauth2.client;

import de.klickreform.dropkit.exception.UnauthorizedException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SimpleOAuthClientServiceTest {

    private static SimpleOAuthClientService clientService;

    @BeforeClass
    public static void setupBeforeClass() throws IOException {
        assertNotNull("Test file missing", SimpleOAuthClientServiceTest.class.getResource("/clients.json"));
        clientService = new SimpleOAuthClientService(SimpleOAuthClientServiceTest.class.getResource("/clients.json").getFile());
    }

    @Test
    public void simpleClientAuthentication() throws UnauthorizedException {
        clientService.authenticate("1");
    }

    @Test
    public void passwordClientAuthentication() throws UnauthorizedException {
        OAuthClient client = clientService.authenticate("1", "secret123");
        assertTrue(client.getIdentifier().equals("1"));
    }

    @Test(expected = UnauthorizedException.class)
    public void clientNotFound() throws UnauthorizedException {
        clientService.authenticate("3");
    }

    @Test(expected = UnauthorizedException.class)
    public void wrongClientSecret() throws UnauthorizedException {
        clientService.authenticate("1", "wrongSecret");
    }

}