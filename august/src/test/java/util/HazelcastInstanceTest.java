package util;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ConcurrentMap;

public class HazelcastInstanceTest {

    @Test
    public void demo( ) {
        Config config = new Config();
        HazelcastInstance h = Hazelcast.newHazelcastInstance(config);
        ConcurrentMap<String, String> map = h.getMap("my-distributed-map");
        map.put("key", "value");
        System.out.println(map.get("key"));
        Assert.assertEquals("value",map.get("key"));

        //Concurrent Map methods
        map.putIfAbsent("somekey", "somevalue");
        map.replace("key", "value", "newvalue");
        System.out.println(map.get("key"));
        Assert.assertEquals("newvalue",map.get("key"));
        h.shutdown();
    }


}
