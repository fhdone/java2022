package com.fhdone.java2022.july.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/deferred")
@Slf4j
public class DeferredResultController {

    @RequestMapping("/resultString")
    @ResponseBody
    public DeferredResult<ResponseEntity<String>> call() { 

        DeferredResult<ResponseEntity<String>> deferredResult =
            new DeferredResult<>(3000L,"timeout called");
        deferredResult.onTimeout(()->{
            log.info("调用超时");
        });

        deferredResult.onCompletion(()->{
            log.info("调用完成");
        });

        new Thread(()->{
            try{
                TimeUnit.SECONDS.sleep(5);
                deferredResult.setResult(new ResponseEntity<>("call ok", HttpStatus.OK));
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        return deferredResult;



    }

}
