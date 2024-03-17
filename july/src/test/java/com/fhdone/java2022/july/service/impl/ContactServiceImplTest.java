package com.fhdone.java2022.july.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.march.dto.demo.Contract;
import com.fhdone.java2022.july.BaseTest;
import com.fhdone.java2022.july.service.ContactService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
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

        List<Contract> list = contactService.queryContact();
        log.info("queryContact result:"+JSON.toJSONString(list));
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void queryContactPage() {

        for(int i=0; i<10; i++){
            List<Contract> list = contactService.queryContact(i ,2);
            PageInfo<Contract> pageInfo = new PageInfo<Contract>(list,10);
            log.info("queryContact result, page num: {}, page info: {}", i ,
                    JSON.toJSONString(pageInfo));
            Assert.assertTrue(list.size()>0);
        }
    }

    @Test
    public void queryContactPage2() {

        for(int i=0; i<10; i++){
            PageInfo pageInfo = contactService.queryContactPage(i ,2);
            log.info("queryContact result, page num: {}, page info: {}", i ,
                    JSON.toJSONString(pageInfo));
            Assert.assertTrue(pageInfo.getTotal()>0);
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
        long count = contactService.insertContact(contract);
        log.info("insertContact result:"+count);
        Assert.assertEquals(1, count);
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
        long count = contactService.insertContact(list);
        log.info("insertContact result:"+count);
        Assert.assertEquals(100, count);
    }

} 
