package com.fhdone.java2022.controller;


import com.fhdone.java2022.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private RedisService redisService;
    
    
    @GetMapping("/")
    public String index() throws Exception {
//        redisService.redisDemo();
        return "Greetings from Spring Boot!";
    }

}