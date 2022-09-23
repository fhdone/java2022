package com.fhdone.java2022.june.controller;

import com.fhdone.java2022.march.dto.ResultInfo;
import com.fhdone.java2022.june.JulyClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/june", produces="application/json")
@Slf4j
@AllArgsConstructor
@DefaultProperties(defaultFallback = "hstrixDefault")
public class JulyController {

    private JulyClient julyClient;

    @GetMapping("/july")
    //@HystrixCommand
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")}
     //,fallbackMethod = "hstrixDefault"
    )
    public ResultInfo july(){
        ResultInfo resultInfo = ResultInfo.instanceSuccess(julyClient.july());
        return resultInfo;
    }

    @GetMapping("/queryContact")
    public ResultInfo queryContact(){
        ResultInfo resultInfo = ResultInfo.instanceSuccess(julyClient.queryContact());
        return resultInfo;
    }


    private ResultInfo hstrixDefault() {
        return ResultInfo.instanceFail("hstrixDefault");
    }
    
}
