package com.fhdone.java2022.august.controller;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.march.dto.demo.Contract;
import com.fhdone.java2022.august.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


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

    @Test
    public void queryContactByCondition() throws Exception {

        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id",6L);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(JSON.toJSONString(paraMap), headers);

        ResponseEntity<Contract[]> response = template.postForEntity("/contract/queryContactByCondition/",
            request, Contract[].class );

        log.info(JSON.toJSONString(response));
        Assert.assertTrue(response.getBody().length>0);
    }


} 
