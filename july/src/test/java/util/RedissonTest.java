package util;

import com.fhdone.java2022.july.BaseTest;
import com.fhdone.java2022.july.config.RedissonConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.redisson.api.RMap;
import org.redisson.api.RRingBuffer;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes= RedissonConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class )  
@Slf4j
public class RedissonTest extends BaseTest {

    @Autowired
    private RedissonClient redissonClient;

    public static final String CERT_MAP = "CERT_MAP";

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



    @Test
    @Order(1)
    public void putKeysToRedisMap(){

        RMap<String, String> rMap = redissonClient.getMap(CERT_MAP);
        rMap.put("testKey", "testValue");

        int i=0;
        while (i<100_000){
            String certNo = RandomStringUtils.random(18, false, true);
            String name = RandomStringUtils.random(20, true, false);
            rMap.put(certNo,name);

            i++;
            if(i%5000==0){
                log.info("{},finished" , i );
            }
        }
    }


    @Test
    @Order(2)
    public void testQueryCert(){

        RMap<String, String> rMap = redissonClient.getMap(CERT_MAP);
        String testValue = rMap.get("testKey");
        Assert.assertEquals("testValue",testValue);
        
        int i=0;
        while (i<100_000) {
            String certNo = RandomStringUtils.random(18, false, true);
            String value = rMap.get(certNo);

            if(StringUtils.isNotBlank(value)){
                log.info("getter! {} ", value);
            }
            i++;
            if(i%5000==0){
                log.info("{},finished" , i );
            }
        }
    }


}
