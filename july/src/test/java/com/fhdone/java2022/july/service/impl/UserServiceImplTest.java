package com.fhdone.java2022.july.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.july.BaseTest;
import com.fhdone.java2022.july.service.ContactService;
import com.fhdone.java2022.july.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;


    @Test
    public void selectUser() throws Exception {
        log.info(JSON.toJSONString(userService.selectUser(null)));
    }


} 
