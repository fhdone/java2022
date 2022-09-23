package com.fhdone.java2022.august.controller;


import com.fhdone.java2022.march.dto.demo.Contract;
import com.fhdone.java2022.august.mapper.demo.ContactMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/contract", produces="application/json")
@AllArgsConstructor
public class ContractController {

    private ContactMapper contactMapper;
    
    @GetMapping("/queryContact")
    public List<Contract> queryContact() throws Exception {
        List<Contract> list = contactMapper.queryContact();
        return list;
    }

}