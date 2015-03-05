package de.klickreform.dropauth.oauth2.token;

import com.hazelcast.core.HazelcastInstance;
import de.klickreform.dropkit.exception.UnauthorizedException;

import java.util.Map;

/**
 * TokenStore implementation based on an in-memory Hazelcast store.
 *
 * @author Benjamin Bestmann
 */
public class HazelcastTokenStore implements TokenStore {

    private HazelcastInstance hazel;
    private Map<String,AccessToken> validTokens;
    private Map<String,String> refreshTokens;

    public HazelcastTokenStore(HazelcastInstance hazel) {
        this.hazel = hazel;
        this.validTokens = hazel.getMap("validTokens");
        this.refreshTokens = hazel.getMap("refreshTokens");
    }

    @Override
    public void storeToken(AccessToken token) {
        validTokens.put(token.getToken(), token);
        refreshTokens.put(token.getRefreshToken(), token.getToken());
    }

    @Override
    public AccessToken resolveToken(String tokenValue) {
        return validTokens.get(tokenValue);
    }

    @Override
    public AccessToken resolveRefreshToken(String refreshToken) throws UnauthorizedException {
        try {
            String oldTokenValue = refreshTokens.get(refreshToken);
            return validTokens.get(oldTokenValue);
        } catch(Exception e) {
            throw new UnauthorizedException("Invalid refresh token");
        }
    }

    @Override
    public void removeToken(String token) {
        validTokens.remove(token);
    }

}
