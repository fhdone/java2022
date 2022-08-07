package com.fhdone.java2022.june.controller;

import com.alibaba.fastjson2.JSON;
import com.fhdone.java2022.june.JuneClient;
import com.fhdone.java2022.june.dto.Contract;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path="/june", produces="application/json")
@Slf4j
@AllArgsConstructor
public class JuneController {

    private JuneClient juneClient;

    @GetMapping("/july")
    @HystrixCommand(fallbackMethod="hstrixDefault")
    public String july(){
        return juneClient.july();
    }

    @GetMapping("/queryContact")
    public List<Contract> queryContact(){
        return  juneClient.queryContact();
    }


    private String hstrixDefault() {
        return "hstrixDefault";
    }
}
