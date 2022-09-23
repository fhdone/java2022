package com.fhdone.java2022.august.service.impl;

import com.fhdone.java2022.august.BaseTest;
import com.fhdone.java2022.august.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisServiceImplTest extends BaseTest {

    @Autowired
    private RedisService redisService;
    
    @Test
    public void redisDemo() throws Exception {
        redisService.redisDemo();
        Assert.assertTrue(true);
    }


} 
