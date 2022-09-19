package com.fhdone.java2022.july.mapper;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.march.dto.demo.Contract;
import com.fhdone.java2022.july.BaseTest;
import com.fhdone.java2022.july.mapper.demo.ContactMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ContactMapperTest extends BaseTest {

    @Autowired
    private ContactMapper contactMapper;

    @Test
    public void queryContact() throws Exception {
        
        for (int i = 0; i < 1; i++) {
            List<Contract> list = contactMapper.queryContact();
            log.info("queryContact result:" + JSON.toJSONString(list));
            Assert.isTrue(list.size()>0, "queryContact pass");
        }
    }

    @Test
    public void queryContactByCondition() throws Exception {
        
        Map<String, Object> map = new HashMap<>();
        List<Contract> list = contactMapper.queryContactByCondition(map);
        log.info("queryContactByCondition result:" + JSON.toJSONString(list));
        Assert.isTrue(list.size()>0, "queryContactByCondition pass");
        
        map.put("id",21L);
        list = contactMapper.queryContactByCondition(map);
        log.info("queryContactByCondition result:" + JSON.toJSONString(list));
        Assert.isTrue(list.size()>0, "queryContactByCondition pass");
        
    }

    @Test
    public void selectContract() throws Exception {
        
        Contract contract = new Contract();
        List<Contract> list = contactMapper.selectContract(contract);
        log.info("selectContract result:" + JSON.toJSONString(list));
        Assert.isTrue(list.size()>0, "selectContract pass");
        
        
    }

    @Test
    public void insertContract() throws Exception {
        Contract contract = new Contract();
        long count = contactMapper.insertContract(contract);
        log.info("insertContract result:" + count);
        Assert.isTrue(count==1, "insertContract pass");
    }
    
}
