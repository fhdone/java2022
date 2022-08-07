package com.fhdone.java2022.june.controller;

import com.alibaba.fastjson2.JSON;
import com.fhdone.java2022.june.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class JuneControllerTest extends BaseTest {

    @Autowired
    private JuneController juneController;
 
    @Test
    public void july() throws Exception {
        log.info(JSON.toJSONString(juneController.july()));
    }

    @Test
    public void queryContact() throws Exception {
        log.info(JSON.toJSONString(juneController.queryContact()));
    }


} 
