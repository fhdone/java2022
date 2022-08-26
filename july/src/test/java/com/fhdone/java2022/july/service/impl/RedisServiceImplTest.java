package com.fhdone.java2022.july.service.impl;

import com.fhdone.java2022.july.BaseTest;
import com.fhdone.java2022.july.service.RedisService;
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
