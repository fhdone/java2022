package com.fhdone.java2022.august.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@Slf4j
public class ExpireCacheTest {

    @Test
    public void testExpire() throws Exception {

        ExpireCache<String> cache = new ExpireCache<>(5, TimeUnit.SECONDS);

        cache.put("test", "test", 1500);
        assertEquals("test", cache.get("test"));

        try {
            TimeUnit.MILLISECONDS.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNull(cache.get("test"));

        cache.put("test2", "test2", 1500);
        assertEquals("test2", cache.get("test2"));
        try {
            TimeUnit.MILLISECONDS.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 用反射拿到私有变量 map
        Field mapField = ExpireCache.class.getDeclaredField("map");
        mapField.setAccessible(true);
        ConcurrentMap<String, Object> map = (ConcurrentMap<String, Object>) mapField.get(cache);
        assertNull(map.get("test2"));
        assertNull(map.get("test2"));
    }

    @Test
    public void testExpire2() throws Exception {

//        ExpireCache<String> cache = new ExpireCache<>(5, TimeUnit.SECONDS);
        Cache<String, String> cache =  CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .removalListener(notification -> log.info("notification={}", JSON.toJSONString(notification)))
                .build();


//        ExpireCache<Map<String, String>> mapCache = new ExpireCache<>(5, TimeUnit.SECONDS);
        Cache<String, Map<String, String>> mapCache =  CacheBuilder.newBuilder()
                .maximumSize(100)
                //配置写入后多久使缓存过期-下文会讲述
                .expireAfterWrite(1, TimeUnit.SECONDS)
                //配置写入后多久刷新缓存-下文会讲述
                //.refreshAfterWrite(1, TimeUnit.SECONDS)
                //key使用弱引用-WeakReference
                //.weakKeys()
                //当Entry被移除时的监听器
                .removalListener(notification -> log.info("notification={}", JSON.toJSONString(notification)))
                .build();

        Map<String, String> mapInfo = new HashMap<>();
        mapInfo.put("mapKey", "mapValue");

        cache.put("testKey", "testValue");
//        cache.invalidateAll();
        mapCache.put("testMap", mapInfo);

        assertEquals("testValue", cache.getIfPresent("testKey"));
        assertEquals("mapValue", mapCache.getIfPresent("testMap").get("mapKey"));


        new Thread(() -> {
            Map<String, String> mapV = mapCache.getIfPresent("testMap");
            for(int i=0; i<20; i++) {
                log.info("count:{}", i);
                assertEquals("mapValue", mapV.get("mapKey"));
                log.info(mapV.get("mapKey"));
                log.info(JSON.toJSONString(mapCache.getIfPresent("testMap")));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //throw new RuntimeException(e);
                }
            }
        }).start();

        try {
            TimeUnit.MILLISECONDS.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNull(cache.getIfPresent("test"));

        TimeUnit.SECONDS.sleep(30);
    }


}