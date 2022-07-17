package com.fhdone.java2022.service.impl;


import com.fhdone.java2022.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RedisServiceImpl implements RedisService {

    private RedisTemplate redisTemplate;

    @Override
    public void redisDemo() throws Exception {
        for(int i=0; i<10; i++) {
            redisTemplate.opsForValue().set("key"+i,
                System.currentTimeMillis()+"",5, TimeUnit.MINUTES );
        }
    }
    
    
}
