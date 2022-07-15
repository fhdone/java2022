package com.fhdone.java2022.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.BaseTest;
import com.fhdone.java2022.service.ContactService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactServiceImplTest extends BaseTest {

    @Autowired
    private ContactService contactService;
    
    @Test
    public void queryContact() throws Exception {
        System.out.println("queryContact result:"+JSON.toJSONString(contactService.queryContact()));
    }


} 
