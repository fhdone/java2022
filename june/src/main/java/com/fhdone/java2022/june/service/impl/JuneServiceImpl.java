package com.fhdone.java2022.june.service.impl;

import com.fhdone.java2022.june.service.JuneService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
@Slf4j
public class JuneServiceImpl implements JuneService {

    /**
     * 请求合并的方法
     */
    @HystrixCollapser(batchMethod="mergeGetById",scope= com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
            collapserProperties={
                    @HystrixProperty(name="maxRequestsInBatch",value="10"),
                    @HystrixProperty(name="timerDelayInMilliseconds",value="5000")
            })
    public Future<String> getById(String id) {
        log.info("======start getById========" + id);
        return null;
    }

    /**
     * 合并请求之后调用的方法
     */
    @HystrixCommand
    public List<String> mergeGetById(List<String> ids) {
        log.info("===start mergeGetById===");
        return ids;
    }


}

