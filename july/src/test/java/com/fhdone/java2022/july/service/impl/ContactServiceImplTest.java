package com.fhdone.java2022.july.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.april.dto.Contract;
import com.fhdone.java2022.july.BaseTest;
import com.fhdone.java2022.july.service.ContactService;
import com.github.pagehelper.PageInfo;
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
    public void queryContactPage() {
        for(int i=0; i<10; i++){
            List<Contract> list = contactService.queryContact(i ,2);
            PageInfo<Contract> pageInfo = new PageInfo<Contract>(list,10);
            log.info("queryContact result, page num: {}, page info: {}", i ,
                JSON.toJSONString(pageInfo));
        }
    }
    
    
    @Test
    @Rollback(true)
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
