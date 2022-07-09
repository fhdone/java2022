package com.fhdone.java2022.demo.service.impl;

import com.fhdone.java2022.demo.BaseTest;
import com.fhdone.java2022.demo.service.RedisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisServiceImplTest extends BaseTest {

    @Autowired
    private RedisService redisService;
    
    @Test
    public void redisDemo() throws Exception {
        redisService.redisDemo();
    }


} 
