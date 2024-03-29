package com.fhdone.java2022.august.controller;


import com.fhdone.java2022.march.dto.demo.Contract;
import com.fhdone.java2022.august.mapper.demo.ContactMapper;
import com.fhdone.java2022.august.service.RedisService;
import com.fhdone.java2022.august.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "HelloController", description = "HelloController desc")
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

    
    @Operation(summary = "test",description = "test desc")
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