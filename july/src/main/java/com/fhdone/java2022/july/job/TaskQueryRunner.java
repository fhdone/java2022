package com.fhdone.java2022.july.job;

import com.fhdone.java2022.july.job.dto.JobDetail;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.core.util.CronExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * query job then offer into JOB_QUEUE[BlockingQueue]
 */
@Component
@EnableScheduling
@Slf4j
public class TaskQueryRunner {

    // ###### TEST USE START ######
    private static final String MOCK_JOB_CRON = "0/5 * * * * ?";
    private static final int RUN_JOB_BATCH_SIZE = 2;
    public static final int JOB_RUN_ELASPE = 10;
    // ###### TEST USE END ######
    
    private static final int MAX_QUEUE_SIZE = 4;
        

    @Getter
    private static BlockingQueue<JobDetail> JOB_QUEUE = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);

    @Scheduled(cron = "0/1 * * * * ?")
    public void taskQueryRunner() {
        log.info("taskQueryRunner start");
        try {
            taskQuery();
        }catch (Exception e){
            log.error("taskQueryRunner error", e);
        }
    }

    /**
     * offer into JOB_QUEUE
     */
    private void taskQuery() throws Exception{

        JobDetail jobDetail = getMockJobDetail();
        CronExpression cronExpression = new CronExpression(jobDetail.getCron());
        Date nextFireTime = jobDetail.getNextFireTime();
        
        if(nextFireTime.before(new Date())){
            log.debug("nextFireTime: {} , now:{}", nextFireTime, new Date());
            this.putTaskIntoQueue(jobDetail);
            jobDetail.setNextFireTime(cronExpression.getTimeAfter(nextFireTime));
        }else{
            log.info("nextFireTime not up to time");
        }
    }

    /**
     * putTaskIntoQueue
     */
    private void putTaskIntoQueue(JobDetail jobDetail) {
        for(int i = 0; i< RUN_JOB_BATCH_SIZE; i++) {
            if(JOB_QUEUE.size() >= MAX_QUEUE_SIZE){
                log.info("JOB_QUEUE is full, ignore");
                continue;
            }
            boolean r = JOB_QUEUE.offer(jobDetail);
            if(!r){
                log.warn("pub job {} error, ignore", jobDetail.getId());
                break;
            }else{
                log.info("success put job: {}" , jobDetail.getId());
            }
        }
    }


    private JobDetail<Map<String, Object>> getMockJobDetail(){
        String jobString = RandomStringUtils.random(20, true, true);
        JobDetail<Map<String, Object>> jobDetail = new JobDetail();
        jobDetail.setId(jobString);
        jobDetail.setCron(MOCK_JOB_CRON);
        jobDetail.setServiceId("DemoTaskServiceImpl");
        jobDetail.setNextFireTime(new Date());
        
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("jobString",jobString);
        jobDetail.setDetail(detailMap);
        
        return jobDetail;
    }

}
