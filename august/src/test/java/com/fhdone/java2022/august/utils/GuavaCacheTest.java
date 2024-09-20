package com.fhdone.java2022.august.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
public class GuavaCacheTest {


    LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    log.info("CacheLoader queryData " + key);
                    return "query Key" + key;
                }

                @Override
                public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                    log.info("CacheLoader reload");
                    return super.reload(key, oldValue);
                }
            });
    
    @Test
    public void test() throws Exception {
        
        cache.put("1", "1");
        log.info(cache.get("1"));
        log.info(cache.get("1"));
        
        TimeUnit.SECONDS.sleep(1);
        for(int i=0; i<10; i++) {
            log.info(cache.get("1"));
            log.info(cache.get("2"));
        }
        
        
        
        
    }

}
