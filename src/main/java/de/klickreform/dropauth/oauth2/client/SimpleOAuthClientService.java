package de.klickreform.dropauth.oauth2.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.klickreform.dropkit.exception.UnauthorizedException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple OAuthClientService that reads a list of OAuthClients
 * from a JSON file.
 *
 * @author Benjamin Bestmann
 */
public class SimpleOAuthClientService implements OAuthClientService {

    private Map<String,OAuthClient> clients;

    public SimpleOAuthClientService(String clientFilePath) throws IOException {
        String jsonInput = readFile(clientFilePath, Charset.defaultCharset());
        final ObjectMapper objectMapper = new ObjectMapper();
        List<OAuthClient> clientList = objectMapper.readValue(jsonInput, objectMapper.getTypeFactory().constructCollectionType(List.class, SimpleOAuthClient.class));
        this.clients = new HashMap<String, OAuthClient>();
        for(OAuthClient client : clientList) {
            this.clients.put(client.getIdentifier(),client);
        }
    }

    @Override
    public OAuthClient authenticate(String identifier) throws UnauthorizedException {
        if(this.clients.containsKey(identifier)) {
            return clients.get(identifier);
        } else {
            throw new UnauthorizedException("Could not authenticate client: " + identifier);
        }
    }

    @Override
    public OAuthClient authenticate(String identifier, String secret) throws UnauthorizedException {
        if(this.clients.containsKey(identifier)) {
            OAuthClient client = this.clients.get(identifier);
            if (client.getSecret().equals(secret)) {
                return client;
            }
        }
        // If this is reached, abort
        throw new UnauthorizedException("Could not authenticate client: " + identifier);
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
