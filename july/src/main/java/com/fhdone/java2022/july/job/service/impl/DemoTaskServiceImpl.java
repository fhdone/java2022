package com.fhdone.java2022.july.job.service.impl;

import com.alibaba.fastjson.JSON;
import com.fhdone.java2022.july.job.TaskQueryRunner;
import com.fhdone.java2022.july.job.dto.JobDetail;
import com.fhdone.java2022.july.job.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * demo task service
 */
@Slf4j
@Service(DemoTaskServiceImpl.SERVICE_ID)
public class DemoTaskServiceImpl implements TaskService<JobDetail>  {

    public static final String SERVICE_ID  = "DemoTaskServiceImpl";

    private static final Random RANDOM = new Random();

    @Override
    public boolean runTaskSuccess(JobDetail jobDetail) {
        log.info("runTaskSuccess process");
        return true;
    }

    @Override
    public boolean runTaskFailed(JobDetail jobDetail) {
        log.info("runTaskFailed process");
        return true;
    }

    @Override
    public boolean runTask(JobDetail jobDetail) {
        try {
            log.info("Start runTask job: {}", JSON.toJSONString(jobDetail));
            TimeUnit.SECONDS.sleep(RANDOM.nextInt(TaskQueryRunner.JOB_RUN_ELASPE));
            
            //simulation error
            if(RANDOM.nextInt(2)%2==0){
                throw new RuntimeException("demo exception eeeeee");
            }
            log.info("End runTask job: {}", JSON.toJSONString(jobDetail));
        } catch (Exception e) {
            log.error("runTask job id:{}, detail:{}  happen error:", 
                jobDetail.getId(), JSON.toJSONString(jobDetail), e);
            return false;
        }
        return true;
    }
}
