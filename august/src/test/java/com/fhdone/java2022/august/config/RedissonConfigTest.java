package com.fhdone.java2022.august.config;

import com.fhdone.java2022.august.BaseTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class RedissonConfigTest extends BaseTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void test1() {
        redissonClient.getBucket("hello").set("bug");
        String test = (String) redissonClient.getBucket("hello").get();
        //System.out.println(test);
        Assert.assertEquals("bug",test);
    }

    @Test
    public void test2() {
        for(int i=0; i<10; i++) {
            RMap map = redissonClient.getMap("map1");
            map.put("key"+i,"value"+i);
            map.expire(3, TimeUnit.MINUTES);
        }
        Assert.assertTrue(true);
    }

    @Test
    @Ignore
    public void testRLock() throws Exception {
        RLock lock = redissonClient.getLock("rlock");    // 拿锁失败时会不停的重试
        // 具有Watch Dog 自动延期机制 默认续30s 每隔30/3=10 秒续到30s
        lock.lock();
        // 尝试拿锁10s后停止重试,返回false 具有Watch Dog 自动延期机制 默认续30s
//        boolean res1 = lock.tryLock(10, TimeUnit.SECONDS);
        // 没有Watch Dog ，10s后自动释放
//        lock.lock(10, TimeUnit.SECONDS);
        // 尝试拿锁100s后停止重试,返回false 没有Watch Dog ，10s后自动释放
//        boolean res2 = lock.tryLock(100, 10, TimeUnit.SECONDS);
        TimeUnit.MINUTES.sleep(3);
        lock.unlock();
    }
    
}
