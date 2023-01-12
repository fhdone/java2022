package com.fhdone.java2022.july.job;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.*;

@Component
@Slf4j
public class TaskExecuteJob {

    private static final int JOB_RUN_ELASPE = 10;

    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("TASK-EXECUTE-JOB-POOL-%d").build();

    private Executor taskExecutor =  new ThreadPoolExecutor(2, 2,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), namedThreadFactory);

    private static final Random RANDOM = new Random();

    @PostConstruct
    public void taskExecuteRunner() {
        log.info("taskExecuteRunner start");
        taskExecutor.execute(new TaskRunner());
        taskExecutor.execute(new TaskRunner());
    }

    class TaskRunner implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    Object obj = TaskQueryRunner.getJOB_QUEUE().take();

                    log.info("Start process job: {}", obj);
                    TimeUnit.SECONDS.sleep(RANDOM.nextInt(TaskExecuteJob.JOB_RUN_ELASPE));
                    log.info("End process job: {}", obj);

                } catch (Exception e) {
                    log.error("taskExecute error:", e);
                }
            }
        }
    }


}
