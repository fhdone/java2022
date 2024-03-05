package com.fhdone.java2022.june.controller;

import com.alibaba.fastjson2.JSON;
import com.fhdone.java2022.june.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void queryContactPage() throws Exception {
        
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("pageNum",1);
        requestMap.put("pageSize",10);
        log.info(JSON.toJSONString(julyController.queryContactPage(requestMap)));
    }

    @Test
    public void demoHstrixDemo() throws Exception {
        for(int i=0; i<100; i++) {
            log.info(JSON.toJSONString(julyController.demoHstrixDemo()));
        }
    }


} 
