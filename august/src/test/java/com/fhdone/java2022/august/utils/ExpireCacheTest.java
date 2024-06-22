package com.fhdone.java2022.august.utils;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ExpireCacheTest {

    @Test
    public void testExpire() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{

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
        ConcurrentMap<String, Object> map =  (ConcurrentMap<String, Object>) mapField.get(cache);
        assertNull(map.get("test2"));
        assertNull(map.get("test2"));
    }



}