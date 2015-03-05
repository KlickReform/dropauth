package de.klickreform.dropauth.oauth2.token;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HazelcastTokenStoreTest {

    private static HazelcastTokenStore tokenStore;

    @BeforeClass
    public static void runBeforeTests() {
        Config cfg = new Config();
        HazelcastInstance hazel = Hazelcast.newHazelcastInstance(cfg);
        tokenStore = new HazelcastTokenStore(hazel);
    }

    @Test
    public void storeAndResolveToken() {
        AccessToken token = new AccessToken("mytoken", "owner", "refreshToken", 3600);
        tokenStore.storeToken(token);
        AccessToken token1 = tokenStore.resolveToken("mytoken");
        assertTrue(token1.getToken().equals("mytoken"));
        assertTrue(token1.getOwner().equals("owner"));
        assertTrue(token1.getRefreshToken().equals("refreshToken"));
        assertTrue(token1.getExpiresIn() == 3600);
    }

    @Test
    public void resolveRefreshToken() {
        AccessToken token = new AccessToken("mytokenr", "owner", "refresh", 3600);
        tokenStore.storeToken(token);
        AccessToken token1 = tokenStore.resolveRefreshToken("refresh");
        assertTrue(token1.getToken().equals("mytokenr"));
    }

    @Test
    public void removeToken() {
        AccessToken token = new AccessToken("mytoken2", "owner", "refreshToken", 3600);
        tokenStore.storeToken(token);
        AccessToken token1 = tokenStore.resolveToken("mytoken2");
        assertTrue(token1.getToken().equals("mytoken2"));
        tokenStore.removeToken("mytoken2");
        AccessToken token2 = tokenStore.resolveToken("mytoken2");
        assertTrue(token2 == null);
    }

}