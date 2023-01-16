package com.fhdone.java2022.july.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.july.job.TaskQueryRunner;
import com.fhdone.java2022.july.job.dto.TaskDetail;
import com.fhdone.java2022.july.job.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * demo task service
 * @author fhdone
 */
@Slf4j
@Service(DemoTaskServiceImpl.SERVICE_ID)
public class DemoTaskServiceImpl implements TaskService<Map<String, Object>> {

    public static final String SERVICE_ID  = "DemoTaskServiceImpl";

    private static final Random RANDOM = new Random();

    @Override
    public boolean runTaskSuccess(TaskDetail<Map<String, Object>> taskDetail) {
        log.info("DemoTaskServiceImpl runTaskSuccess process");
        return true;
    }

    @Override
    public boolean runTaskFailed(TaskDetail<Map<String, Object>> taskDetail) {
        log.info("DemoTaskServiceImpl runTaskFailed process");
        return true;
    }

    @Override
    public boolean runTask(TaskDetail<Map<String, Object>> taskDetail) {
        try {
            log.info("DemoTaskServiceImpl start runTask job: {}", JSON.toJSONString(taskDetail));
            TimeUnit.SECONDS.sleep(RANDOM.nextInt(TaskQueryRunner.JOB_RUN_ELASPE));
            
            //simulation error
            if(RANDOM.nextInt(2)%2==0){
                throw new RuntimeException("DemoTaskServiceImpl demo exception eeeeee");
            }
            log.info("DemoTaskServiceImpl end runTask job: {}", JSON.toJSONString(taskDetail));
        } catch (Exception e) {
            log.error("DemoTaskServiceImpl runTask job id:{}, detail:{}  happen error:", 
                taskDetail.getId(), JSON.toJSONString(taskDetail), e);
            return false;
        }
        return true;
    }
}
