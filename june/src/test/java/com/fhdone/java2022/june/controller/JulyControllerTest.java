package com.fhdone.java2022.june.controller;

import com.alibaba.fastjson2.JSON;
import com.fhdone.java2022.june.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class JulyControllerTest extends BaseTest {

    @Autowired
    private JulyController julyController;
 
    @Test
    public void july() throws Exception {
        log.info(JSON.toJSONString(julyController.july()));
    }

    @Test
    public void queryContact() throws Exception {
        log.info(JSON.toJSONString(julyController.queryContact()));
    }


} 