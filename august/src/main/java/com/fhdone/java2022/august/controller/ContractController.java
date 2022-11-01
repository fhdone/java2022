package com.fhdone.java2022.august.controller;


import com.fhdone.java2022.march.dto.demo.Contract;
import com.fhdone.java2022.august.mapper.demo.ContactMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    
    @PostMapping("/queryContactByCondition")
    public List<Contract> queryContactByCondition(@RequestBody Map<String, Object> requestMap,
                                                  HttpServletRequest request) throws Exception {
        
        List<Contract> list = contactMapper.queryContactByCondition(requestMap);
        return list;
    }

}