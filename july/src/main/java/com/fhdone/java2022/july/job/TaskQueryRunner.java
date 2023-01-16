package com.fhdone.java2022.july.job;

import com.fhdone.java2022.july.job.dto.TaskDetail;
import com.fhdone.java2022.july.utils.TaskUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.CronExpression;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * query job then offer into JOB_QUEUE[BlockingQueue]
 * @author fhdone
 */
@Component
@EnableScheduling
@Slf4j
public class TaskQueryRunner {

    // ###### TEST USE START ######
    private static final int RUN_JOB_BATCH_SIZE = 2;
    public static final int JOB_RUN_ELASPE = 10;
    // ###### TEST USE END ######
    
    private static final int MAX_QUEUE_SIZE = 4;
        

    @Getter
    private static BlockingQueue<TaskDetail<?>> JOB_QUEUE = new ArrayBlockingQueue<>(MAX_QUEUE_SIZE);

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

        TaskDetail<?> taskDetail = TaskUtils.getMockJobDetail();
        CronExpression cronExpression = new CronExpression(taskDetail.getCron());
        Date nextFireTime = taskDetail.getNextFireTime();
        
        if(nextFireTime.before(new Date())){
            log.debug("nextFireTime: {} , now:{}", nextFireTime, new Date());
            this.putTaskIntoQueue(taskDetail);
            taskDetail.setNextFireTime(cronExpression.getTimeAfter(nextFireTime));
        }else{
            log.info("nextFireTime not up to time");
        }
    }

    /**
     * putTaskIntoQueue
     */
    private void putTaskIntoQueue(TaskDetail<?>  taskDetail) {
        for(int i = 0; i< RUN_JOB_BATCH_SIZE; i++) {
            if(JOB_QUEUE.size() >= MAX_QUEUE_SIZE){
                log.info("JOB_QUEUE is full, ignore");
                continue;
            }
            boolean r = JOB_QUEUE.offer(taskDetail);
            if(!r){
                log.warn("pub job {} error, ignore", taskDetail.getId());
                break;
            }else{
                log.info("success put job: {}" , taskDetail.getId());
            }
        }
    }


}
