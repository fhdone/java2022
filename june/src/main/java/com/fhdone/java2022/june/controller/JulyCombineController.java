package com.fhdone.java2022.june.controller;

import com.fhdone.java2022.june.service.impl.JuneServiceImpl;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@RestController
@AllArgsConstructor
@RequestMapping(path="/juneCombine", produces="application/json")
@Slf4j
public class JulyCombineController {

    private JuneServiceImpl juneService;

    @RequestMapping(value = "/getById/{id}")
    public String getById(@PathVariable String id) throws Exception {
        Future<String> items = juneService.getById(id);
        return items.get();
    }

    @GetMapping("/combineTest")
    public void test4() throws ExecutionException, InterruptedException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        Future<String> q1 = juneService.getById("1");
        Future<String> q2 = juneService.getById("2");
        Future<String> q3 = juneService.getById("3");

        String str1 = q1.get();
        String str2 = q2.get();
        String str3 = q3.get();

        Thread.sleep(3000);

        Future<String> q4 = juneService.getById("4");
        String str4 = q4.get();
        log.info("str1:{}", str1);
        log.info("str2:{}", str2);
        log.info("str3:{}", str3);
        log.info("str4:{}", str4);

        context.close();
    }

}