package com.fhdone.java2022.controller;


import com.fhdone.java2022.dto.Contract;
import com.fhdone.java2022.mapper.ContactMapper;
import com.fhdone.java2022.service.ContactService;
import com.fhdone.java2022.service.RedisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}