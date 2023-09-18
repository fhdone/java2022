package com.fhdone.java2022.august.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.august.BaseTest;
import com.fhdone.java2022.august.service.UserService;
import com.fhdone.java2022.march.dto.test.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

@Slf4j
public class UserServiceImpl_Spi_Test  {

    @Test
    public void spiTest() {
        ServiceLoader<UserService> loadBalanceServiceLoader = ServiceLoader.load(UserService.class);
        Iterator<UserService> iterator = loadBalanceServiceLoader.iterator();
        while (iterator.hasNext()) {
            UserService userService = iterator.next();
            System.out.println("获取到服务:" + userService);
        }
    }

} 
