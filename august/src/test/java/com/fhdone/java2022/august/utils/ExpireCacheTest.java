package com.fhdone.java2022.august.utils;

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

        ExpireCache<String> cache = new ExpireCache<>(5, TimeUnit.SECONDS);
        ExpireCache<Map<String, String>> mapCache = new ExpireCache<>(5, TimeUnit.SECONDS);

        Map<String, String> mapInfo = new HashMap<>();
        mapInfo.put("mapKey", "mapValue");

        cache.put("testKey", "testValue", 1500);
        mapCache.put("testMap", mapInfo, 1500);

        assertEquals("testValue", cache.get("testKey"));
        assertEquals("mapValue", mapCache.get("testMap").get("mapKey"));


        new Thread(() -> {
            Map<String, String> mapV = mapCache.get("testMap");
            for(int i=0; i<20; i++) {
                log.info("count:{}", i);
                assertEquals("mapValue", mapV.get("mapKey"));
                log.info(mapV.get("mapKey"));
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
        assertNull(cache.get("test"));
        
        TimeUnit.SECONDS.sleep(30);
    }


}