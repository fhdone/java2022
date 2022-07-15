package com.fhdone.java2022.service.impl;

import com.fhdone.java2022.dto.Contract;
import com.fhdone.java2022.mapper.ContactMapper;
import com.fhdone.java2022.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactMapper contactMapper;
    
    @Override
    public List<Contract> queryContact() {
        return contactMapper.queryContact();
    }
    
}
