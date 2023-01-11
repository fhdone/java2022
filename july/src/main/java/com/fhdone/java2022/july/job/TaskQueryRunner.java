package com.fhdone.java2022.july.job;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


@Component
@EnableScheduling
@Slf4j
public class TaskQueryRunner {

    private static final int MAX_QUEUE_SIZE = 10;

    @Getter
    private static BlockingQueue<Object> JOB_QUEUE = new ArrayBlockingQueue<Object>(MAX_QUEUE_SIZE);


    @Scheduled(cron = "0/1 * * * * ?")
    public void taskQueryRunner() {
        log.info("taskQueryRunner start");
        taskQuery();
    }

    private void taskQuery(){
        for(int i=0; i<5; i++) {
            String jobString = RandomStringUtils.random(20, true, true);
            boolean r = JOB_QUEUE.offer(jobString);
            if(!r){
                log.warn("pub job {} error, ignore", jobString);
                break;
            }else{
                log.info("success put job: {}" , jobString);
            }
        }
    }


}
