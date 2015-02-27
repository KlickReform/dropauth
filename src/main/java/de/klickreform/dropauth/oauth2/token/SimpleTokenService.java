package de.klickreform.dropauth.oauth2.token;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import de.klickreform.dropauth.oauth2.owner.ResourceOwner;
import com.hazelcast.config.Config;

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
    public AccessToken createAccessToken(ResourceOwner resourceOwner) {
        String tokenValue = tokenGenerator.generateToken();
        AccessToken token = new AccessToken(tokenValue, resourceOwner.getIdentifier(), this.expiresIn);
        tokenStore.storeToken(token);
        return token;
    }

    @Override
    public AccessToken resolveToken(String token) {
        return tokenStore.resolveToken(token);
    }

}
