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
        AccessToken token = new AccessToken("mytoken", "owner", 3600);
        tokenStore.storeToken(token);
        AccessToken token1 = tokenStore.resolveToken("mytoken");
        assertTrue(token1.getToken().equals("mytoken"));
        assertTrue(token1.getOwner().equals("owner"));
        assertTrue(token1.getExpiresIn() == 3600);
    }

}