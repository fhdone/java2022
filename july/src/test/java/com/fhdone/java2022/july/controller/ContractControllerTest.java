package com.fhdone.java2022.july.controller;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.march.dto.demo.Contract;
import com.fhdone.java2022.july.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;


@Slf4j
public class ContractControllerTest extends BaseTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void queryContact() throws Exception {
        ResponseEntity<Contract[]> response = template.getForEntity("/contract/queryContact",Contract[].class);
        log.info(JSON.toJSONString(response));
        Assert.assertTrue(response.getBody().length>0);
    }


} 
