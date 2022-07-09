package com.fhdone.java2022.demo.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class ReidsConfigTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void redisDemo() throws Exception {
        for(int i=0; i<10; i++) {
            redisTemplate.opsForValue().set("key"+i, System.currentTimeMillis()+"" );
        }
    }


} 
