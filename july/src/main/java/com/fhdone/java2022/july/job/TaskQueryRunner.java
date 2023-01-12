package com.fhdone.java2022.july.job;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.core.util.CronExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * query job then offer into JOB_QUEUE[BlockingQueue]
 */
@Component
@EnableScheduling
@Slf4j
public class TaskQueryRunner {

    private static final int MAX_QUEUE_SIZE = 4;
    private static final String MOCK_JOB_CRON = "0/5 * * * * ?";
    private static final int GEN_JOB_BATCH_SIZE = 2;
    
    private Date nextFireTime = new Date();

    @Getter
    private static BlockingQueue<Object> JOB_QUEUE = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);

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

        CronExpression cronExpression = new CronExpression(MOCK_JOB_CRON);
        if(this.nextFireTime.before(new Date())){
            log.debug("nextFireTime: {} , now:{}", nextFireTime, new Date());
            this.nextFireTime = cronExpression.getTimeAfter(this.nextFireTime);
            
            for(int i=0; i<GEN_JOB_BATCH_SIZE; i++) {
                String jobString = RandomStringUtils.random(20, true, true);
                if(JOB_QUEUE.size() >= MAX_QUEUE_SIZE){
                    log.info("JOB_QUEUE is full, ignore");
                    continue;
                }
                boolean r = JOB_QUEUE.offer(jobString);
                if(!r){
                    log.warn("pub job {} error, ignore", jobString);
                    break;
                }else{
                    log.info("success put job: {}" , jobString);
                }
            }
        }else{
            log.info("nextFireTime not up to time");
        }
    }


}
