package com.fhdone.java2022.july.controller;


import com.fhdone.java2022.april.dto.Contract;
import com.fhdone.java2022.july.mapper.ContactMapper;
import com.fhdone.java2022.july.service.RedisService;
import com.fhdone.java2022.july.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class HelloController {

    private RedisService redisService;

    private ContactService contactService;

    private ContactMapper contactMapper;
    
    @GetMapping("/")
    public String index() throws Exception {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/test")
    public String test() throws Exception {
        redisService.redisDemo();
        contactMapper.queryContact();
        contactService.queryContact();
        return "test ok";
    }
    
    @GetMapping("/queryContact")
    public List<Contract> queryContact() throws Exception {
        List<Contract> list = contactMapper.queryContact();
        return list;
    }

}