package com.fhdone.java2022.demo.service.impl;


import com.fhdone.java2022.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void redisDemo() throws Exception {
        for(int i=0; i<10; i++) {
            redisTemplate.opsForValue().set("key"+i,
                System.currentTimeMillis()+"",5, TimeUnit.MINUTES );
        }
    }
    
    
}
