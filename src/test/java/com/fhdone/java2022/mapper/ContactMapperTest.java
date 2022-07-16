package com.fhdone.java2022.mapper;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.BaseTest;
import com.fhdone.java2022.dto.Contract;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ContactMapperTest extends BaseTest {

    @Autowired
    private ContactMapper contactMapper;

    @Test
    public void queryContact() throws Exception {
        for (int i = 0; i < 1; i++) {
            log.info("queryContact result:" + JSON.toJSONString(contactMapper.queryContact()));
        }
    }

    @Test
    public void queryContactByCondition() throws Exception {
        Map<String, Object> map = new HashMap<>();
        log.info("queryContact result:" + JSON.toJSONString(contactMapper.queryContactByCondition(map)));

        map.put("id",21L);
        log.info("queryContact result:" + JSON.toJSONString(contactMapper.queryContactByCondition(map)));
    }

    @Test
    public void selectContract() throws Exception {
        Contract contract = new Contract();
        log.info("queryContact result:" + JSON.toJSONString(contactMapper.selectContract(contract)));
    }

    @Test
    public void insertContract() throws Exception {
        Contract contract = new Contract();
        log.info("insertContract result:" + contactMapper.insertContract(contract));
    }
    
}
