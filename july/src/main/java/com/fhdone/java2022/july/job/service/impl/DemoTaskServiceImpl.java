package com.fhdone.java2022.july.job.service.impl;

import com.fhdone.java2022.july.job.TaskQueryRunner;
import com.fhdone.java2022.july.job.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service(DemoTaskServiceImpl.SERVICE_ID)
public class DemoTaskServiceImpl implements TaskService {

    public static final String SERVICE_ID  = "DemoTaskServiceImpl";

    private static final Random RANDOM = new Random();

    @Override
    public boolean runTaskSuccess(Object obj) {
        log.info("runTaskSuccess process");
        return true;
    }

    @Override
    public boolean runTaskFailed(Object obj) {
        log.info("runTaskFailed process");
        return true;
    }

    @Override
    public boolean runTask(Object obj){
        try {
            log.info("Start runTask job: {}", obj);
            TimeUnit.SECONDS.sleep(RANDOM.nextInt(TaskQueryRunner.JOB_RUN_ELASPE));
            log.info("End runTask job: {}", obj);
            
            if(RANDOM.nextInt(2)%2==0){
                throw new RuntimeException("demo exception eeeeee");
            }
            
        } catch (Exception e) {
            log.error("runTask error:", e);
            return false;
        }
        return true;
    }
}
