package util;

import com.fhdone.java2022.july.BaseTest;
import com.fhdone.java2022.july.config.RedissonConfig;
import org.junit.Test;
import org.redisson.api.RRingBuffer;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes= RedissonConfig.class)
public class RedissonTest extends BaseTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testRing() throws Exception {
        RRingBuffer<Integer> buffer = redissonClient.getRingBuffer("ring");

// buffer capacity is 4 elements
        buffer.trySetCapacity(4);

        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4);

// buffer state is 1, 2, 3, 4

        buffer.add(5);
        buffer.add(6);

// buffer state is 3, 4, 5, 6
    }

}
