package de.klickreform.dropauth.oauth2.scope;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.klickreform.dropauth.OAuthSettings;
import de.klickreform.dropauth.exceptions.InvalidScopeException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple implementation of the ScopeService interface that parses
 * a list of valid scopes from a JSON file.
 *
 * @author Benjamin Bestmann
 */
public class SimpleScopeService implements ScopeService {

    private Map<String,Scope> scopes;

    public SimpleScopeService(String scopeFilePath) throws IOException {
        String jsonInput = readFile(scopeFilePath, Charset.defaultCharset());
        final ObjectMapper objectMapper = new ObjectMapper();
        List<Scope> scopeList = objectMapper.readValue(jsonInput, objectMapper.getTypeFactory().constructCollectionType(List.class, SimpleScope.class));
        this.scopes = new HashMap<String, Scope>();
        for(Scope scope : scopeList) {
            this.scopes.put(scope.getDesignator(),scope);
        }
    }

    @Override
    public ScopeSet parseValidScopes(String scopeString) throws InvalidScopeException {
        ScopeSet validScopes = new ScopeSet();
        ScopeSet invalidScopes = new ScopeSet();
        String[] scopes = scopeString.split(OAuthSettings.SCOPE_DELIMITER);
        for(String scope : scopes) {
            if(this.scopes.containsKey(scope)) {
                // If a scope with the designator could be found in the Map, add it's title to the list
                validScopes.add(scope);
            } else {
                invalidScopes.add(scope);
            }
        }
        if(invalidScopes.size() != 0) {
            // If there is at least one unknown scope, throw InvalidScopeException
            throw new InvalidScopeException("Invalid or unknown Scope: " + invalidScopes.toString());
        }
        return validScopes;
    }

    @Override
    public Map<String,Scope> getAllValidScopes() {
        return this.scopes;
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
