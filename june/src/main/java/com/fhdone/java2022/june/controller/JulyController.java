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

import java.util.Random;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping(path="/june", produces="application/json")
@Slf4j
@AllArgsConstructor
@DefaultProperties(defaultFallback = "hstrixDefault")
public class JulyController {

    private JulyClient julyClient;

    @GetMapping("/july")
    @HystrixCommand
    public ResultInfo july(){
        ResultInfo resultInfo = ResultInfo.instanceSuccess(julyClient.july());
        return resultInfo;
    }

    @GetMapping("/queryContact")
    @HystrixCommand( 
        commandProperties = {
        @HystrixProperty(
            name="execution.isolation.thread.timeoutInMilliseconds",
            value="500")
    })
    public ResultInfo queryContact() throws Exception {
        ResultInfo resultInfo = ResultInfo.instanceSuccess(julyClient.queryContact());
        return resultInfo;
    }

    @GetMapping("/hstrixDemo")
    @HystrixCommand( 
        commandProperties = {
        @HystrixProperty(
            name="execution.isolation.thread.timeoutInMilliseconds",
            value="200")
//        @HystrixProperty(
//            name="circuitBreaker.requestVolumeThreshold",
//            value="2"),
//        @HystrixProperty(
//            name="circuitBreaker.errorThresholdPercentage",
//            value="2"),
//        @HystrixProperty(
//            name="metrics.rollingStats.timeInMilliseconds",
//            value="1000")
//        @HystrixProperty(
//            name="circuitBreaker.sleepWindowInMilliseconds",
//            value="1000"),
//        @HystrixProperty(
//            name="execution.isolation.strategy",
//            value="SEMAPHORE"),     
//        @HystrixProperty(
//            name="execution.isolation.semaphore.maxConcurrentRequests",
//            value="2")    
    })
    public ResultInfo hstrixDemo() throws Exception {
        
        Random r = new Random();
        if(r.nextInt()%5==0){
            throw new Exception();
        }
            
        ResultInfo resultInfo = ResultInfo.instanceSuccess(julyClient.queryContact());
        TimeUnit.MILLISECONDS.sleep(r.nextInt(r.nextInt(1000)));
        return resultInfo;
    }
    
    
    
    
    private ResultInfo hstrixDefault() {
        log.warn("发生服务降级");
        return ResultInfo.instanceFail("hstrixDefault");
    }
    
}