package com.fhdone.java2022.july.controller;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.april.dto.Contract;
import com.fhdone.java2022.july.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Slf4j
public class ContractControllerTest extends BaseTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void queryContact() throws Exception {
        ResponseEntity<Contract[]> response = template.getForEntity("/contract/queryContact",Contract[].class);
        log.info(JSON.toJSONString(response));
    }


} 
