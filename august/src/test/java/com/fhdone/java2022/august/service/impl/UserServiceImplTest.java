package com.fhdone.java2022.august.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.march.dto.test.User;
import com.fhdone.java2022.august.BaseTest;
import com.fhdone.java2022.august.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;


    @Test
    public void selectUser() throws Exception {

        List<User> list = userService.selectUser(null);
        log.info(JSON.toJSONString(list));
        Assert.assertTrue(list.size()>0);
    }


} 
