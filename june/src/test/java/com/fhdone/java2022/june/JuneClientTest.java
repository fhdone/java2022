package com.fhdone.java2022.june;

import com.alibaba.fastjson2.JSON;
import com.fhdone.java2022.june.dto.Contract;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class JuneClientTest extends BaseTest{

    @Autowired
    private JuneClient juneClient;

    @Test
    public void july(){
        log.info(JSON.toJSONString(juneClient.july()));
    }

    @Test
    public void queryContact(){
        for(int i=0; i<100; i++) {
            List<Contract> list = juneClient.queryContact();
            log.info(JSON.toJSONString(list));
        }
    }
    
}
