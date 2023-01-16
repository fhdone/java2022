package com.fhdone.java2022.july.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.july.job.TaskQueryRunner;
import com.fhdone.java2022.july.job.dto.TaskDetail;
import com.fhdone.java2022.july.job.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * demo task service
 * @author fhdone
 */
@Slf4j
@Service(Demo2TaskServiceImpl.SERVICE_ID)
public class Demo2TaskServiceImpl implements TaskService<String> {

    public static final String SERVICE_ID  = "Demo2TaskServiceImpl";

    private static final Random RANDOM = new Random();

    @Override
    public boolean runTaskSuccess(TaskDetail<String> taskDetail) {
        log.info("Demo2TaskServiceImpl runTaskSuccess process");
        return true;
    }

    @Override
    public boolean runTaskFailed(TaskDetail<String> taskDetail) {
        log.info("Demo2TaskServiceImpl runTaskFailed process");
        return true;
    }

    @Override
    public boolean runTask(TaskDetail<String> taskDetail) {
        try {
            log.info("Demo2TaskServiceImpl start runTask job: {}", JSON.toJSONString(taskDetail));
            TimeUnit.SECONDS.sleep(RANDOM.nextInt(TaskQueryRunner.JOB_RUN_ELASPE));
            
            //simulation error
            if(RANDOM.nextInt(2)%2==0){
                throw new RuntimeException("Demo2TaskServiceImpl demo exception eeeeee");
            }
            log.info("Demo2TaskServiceImpl end runTask job: {}", JSON.toJSONString(taskDetail));
        } catch (Exception e) {
            log.error("Demo2TaskServiceImpl runTask job id:{}, detail:{}  happen error:", 
                taskDetail.getId(), JSON.toJSONString(taskDetail), e);
            return false;
        }
        return true;
    }
}
