package de.klickreform.dropauth.oauth2.token;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import de.klickreform.dropauth.oauth2.client.OAuthClient;
import de.klickreform.dropauth.oauth2.owner.ResourceOwner;
import com.hazelcast.config.Config;
import de.klickreform.dropauth.oauth2.scope.ScopeSet;
import de.klickreform.dropkit.exception.UnauthorizedException;

/**
 * Simple implementation of TokenService using a Hazelcast in memory store
 * to keep track of issued tokens.
 *
 * @author Benjamin Bestmann
 */
public class SimpleTokenService implements TokenService {

    private TokenGenerator tokenGenerator;
    private TokenStore tokenStore;
    private int expiresIn;

    public SimpleTokenService(int expiresIn) {
        this.tokenGenerator = new UUIDTokenGenerator();
        Config cfg = new Config();
        HazelcastInstance hazel = Hazelcast.newHazelcastInstance(cfg);
        this.tokenStore = new HazelcastTokenStore(hazel);
        this.expiresIn = expiresIn;
    }

    @Override
    public AccessToken createAccessToken(OAuthClient client, ResourceOwner resourceOwner, ScopeSet scopeSet) {
        String tokenValue = tokenGenerator.generateToken();
        String refreshToken = tokenGenerator.generateToken();
        AccessToken token = new AccessToken(tokenValue, client.getIdentifier(), resourceOwner.getIdentifier(), scopeSet.toString(), refreshToken, this.expiresIn);
        tokenStore.storeToken(token);
        return token;
    }

    @Override
    public AccessToken resolveToken(String token) {
        return tokenStore.resolveToken(token);
    }

    @Override
    public AccessToken refreshToken(OAuthClient client, String refreshToken) throws UnauthorizedException {
        AccessToken oldToken = tokenStore.resolveRefreshToken(refreshToken);
        if(!client.getIdentifier().equals(oldToken.getClient())) {
            throw new UnauthorizedException("Refresh token not valid for this client.");
        }
        tokenStore.removeToken(oldToken.getToken());
        String newTokenValue = tokenGenerator.generateToken();
        AccessToken newToken = new AccessToken(newTokenValue, oldToken.getClient(), oldToken.getOwner(), oldToken.getScopes(), refreshToken, this.expiresIn);
        tokenStore.storeToken(newToken);
        return newToken;
    }

    @Override
    public void revokeToken(String token) {
        this.tokenStore.removeToken(token);
    }

}
