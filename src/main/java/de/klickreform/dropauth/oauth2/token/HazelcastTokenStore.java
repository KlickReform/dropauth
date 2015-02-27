package de.klickreform.dropauth.oauth2.token;

import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

/**
 * TokenStore implementation based on an in-memory Hazelcast store.
 *
 * @author Benjamin Bestmann
 */
public class HazelcastTokenStore implements TokenStore {

    private HazelcastInstance hazel;
    private Map<String,AccessToken> validTokens;

    public HazelcastTokenStore(HazelcastInstance hazel) {
        this.hazel = hazel;
        this.validTokens = hazel.getMap("validTokens");
    }

    @Override
    public void storeToken(AccessToken token) {
        validTokens.put(token.getToken(), token);
    }

    @Override
    public AccessToken resolveToken(String tokenValue) {
        return validTokens.get(tokenValue);
    }

}
