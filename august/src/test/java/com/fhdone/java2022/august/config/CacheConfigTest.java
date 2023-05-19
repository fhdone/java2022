package com.fhdone.java2022.august.config;

import com.fhdone.java2022.august.BaseTest;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CacheConfigTest extends BaseTest {

    @Autowired
    private Cache<String, Object> cache;

    @Test
    public void test_1() throws Exception {
        cache.put("key", "value");
        log.info(cache.getIfPresent("key")+"");
        Assert.assertEquals("value",cache.getIfPresent("key"));

        TimeUnit.SECONDS.sleep(CacheConfig.DURATION);
        Assert.assertNull(cache.getIfPresent("key"));
    }


    @Test
    public void test_2() throws Exception {

        cache.policy().expireVariably().ifPresent(e->{
            e.put("a", "1", 1, TimeUnit.SECONDS);
            e.put("b", "2", 5, TimeUnit.SECONDS);
        });

        cache.policy().expireVariably().ifPresent(e->{
            Assert.assertNotNull(e.getExpiresAfter("a"));
            Assert.assertNotNull(e.getExpiresAfter("b"));
        });
        TimeUnit.SECONDS.sleep(2);

        cache.policy().expireVariably().ifPresent(e->{
            Assert.assertNull(e.getExpiresAfter("a"));
            Assert.assertNotNull(e.getExpiresAfter("b"));
        });
        TimeUnit.SECONDS.sleep(3);


        cache.policy().expireVariably().ifPresent(e->{
            Assert.assertNull(e.getExpiresAfter("a"));
            Assert.assertNull(e.getExpiresAfter("b"));
        });

    }
}