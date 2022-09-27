package com.fhdone.java2022.june.controller;

import com.fhdone.java2022.june.JulyClient;
import com.fhdone.java2022.march.dto.ResultInfo;
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
    @HystrixCommand(fallbackMethod = "hstrixDefault", 
        commandProperties = {
        @HystrixProperty(
            name="execution.isolation.thread.timeoutInMilliseconds",
            value="1000")}
    )
    public ResultInfo july(){
        ResultInfo resultInfo = ResultInfo.instanceSuccess(julyClient.july());
        return resultInfo;
    }

    @GetMapping("/queryContact")
    @HystrixCommand(fallbackMethod = "hstrixDefault", 
        commandProperties = {
        @HystrixProperty(
            name="execution.isolation.thread.timeoutInMilliseconds",
            value="1000"),
        @HystrixProperty(
            name="circuitBreaker.requestVolumeThreshold",
            value="2"),
        @HystrixProperty(
            name="circuitBreaker.errorThresholdPercentage",
            value="2"),
        @HystrixProperty(
            name="metrics.rollingStats.timeInMilliseconds",
            value="1000"),
        @HystrixProperty(
            name="circuitBreaker.sleepWindowInMilliseconds",
            value="1000")
    })
    public ResultInfo queryContact() throws Exception {
        ResultInfo resultInfo = ResultInfo.instanceSuccess(julyClient.queryContact());
//        TimeUnit.SECONDS.sleep(1000);
        return resultInfo;
    }


    private ResultInfo hstrixDefault() {
        return ResultInfo.instanceFail("hstrixDefault");
    }
    
}
