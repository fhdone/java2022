package com.fhdone.java2022.mapper;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class ContactMapperTest extends BaseTest {

    @Autowired
    private ContactMapper contactMapper;

    @Test
    public void findContact() throws Exception {
        for (int i = 0; i < 3; i++) {
            System.out.println("findContact:" + JSON.toJSONString(contactMapper.findContact()));
        }
    }
    
}
