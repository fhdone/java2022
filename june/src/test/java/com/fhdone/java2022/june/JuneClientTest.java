package com.fhdone.java2022.june;

import com.alibaba.fastjson2.JSON;
import com.fhdone.java2022.april.dto.demo.Contract;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class JuneClientTest extends BaseTest{

    @Autowired
    private JuneClient juneClient;

    @Test
    public void july(){
        String result = JSON.toJSONString(juneClient.july());
        log.info(result);
        Assert.assertEquals("\"test ok\"", result);
    }

    @Test
    public void queryContact(){
        for(int i=0; i<100; i++) {
            List<Contract> list = juneClient.queryContact();
            log.info(JSON.toJSONString(list));
        }
    }
    
}
