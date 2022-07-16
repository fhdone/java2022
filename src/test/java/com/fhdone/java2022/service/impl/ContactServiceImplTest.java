package com.fhdone.java2022.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.BaseTest;
import com.fhdone.java2022.dto.Contract;
import com.fhdone.java2022.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ContactServiceImplTest extends BaseTest {

    @Autowired
    private ContactService contactService;
    
    @Test
    public void queryContact() throws Exception {
       log.info("queryContact result:"+JSON.toJSONString(contactService.queryContact()));
    }
    
    @Test
    @Rollback(false)
    public void insertContact(){
        Contract contract = new Contract();
        contract.setEmail("email");
        contract.setFirstname("firstname");
        contract.setLastname("lastname");
        contract.setTelephone("tel");
        log.info("insertContact result:"+contactService.insertContact(contract));
    }

    @Test
    @Rollback(true)
    public void insertContactList(){
        List<Contract> list = new ArrayList<>();
        for(int i=0; i<100; i++){
            Contract contract = new Contract();
            contract.setEmail("email:"+i);
            contract.setFirstname("firstname:"+i);
            contract.setLastname("lastname:"+i);
            contract.setTelephone("tel:"+i);
            list.add(contract);
        }
        log.info("insertContact result:"+contactService.insertContact(list));
    }

} 
