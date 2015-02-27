package de.klickreform.dropauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.klickreform.dropauth.oauth2.AuthorizationServer;
import de.klickreform.dropauth.oauth2.client.OAuthClientService;
import de.klickreform.dropauth.oauth2.client.SimpleOAuthClientService;
import de.klickreform.dropauth.oauth2.owner.ResourceOwnerAuthProvider;
import de.klickreform.dropauth.oauth2.scope.ScopeService;
import de.klickreform.dropauth.oauth2.scope.SimpleScopeService;
import de.klickreform.dropauth.oauth2.token.SimpleTokenService;
import de.klickreform.dropauth.oauth2.token.TokenService;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * Factory to create AuthorizationServer instances that get casted
 * from the Configuration File.
 *
 * @author Benjamin Bestmann
 */
public class DropauthFactory {

    private String providerName = "oauth-provider";
    private int tokenExpirationTime = 3600;
    private String clientsFile;
    private String scopesFile;

    @JsonProperty
    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @NotNull
    @JsonProperty
    public int getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(int tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    @NotEmpty
    @JsonProperty
    public String getClientsFile() {
        return clientsFile;
    }

    public void setClientsFile(String clientsFile) {
        this.clientsFile = clientsFile;
    }

    @NotEmpty
    @JsonProperty
    public String getScopesFile() {
        return scopesFile;
    }

    public void setScopesFile(String scopesFile) {
        this.scopesFile = scopesFile;
    }

    public AuthorizationServer buildServer(ResourceOwnerAuthProvider resourceOwnerAuthProvider) throws IOException {
        TokenService tokenService = new SimpleTokenService(this.tokenExpirationTime);
        OAuthClientService clientService = new SimpleOAuthClientService(this.clientsFile);
        ScopeService scopeService = new SimpleScopeService(this.scopesFile);
        AuthorizationServer authServer = new AuthorizationServer(tokenService,resourceOwnerAuthProvider,scopeService,clientService);
        return authServer;
    }

}
