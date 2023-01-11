package com.fhdone.java2022.july.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class TaskExecuteJob {

    @PostConstruct
    public void taskExecuteRunner() {
        log.info("taskExecuteRunner start");
        taskExecute();
    }

    private void taskExecute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Object obj = TaskQueryRunner.getJOB_QUEUE().take();
                        log.info("get job: {}", obj.toString());
                    } catch (Exception e) {
                        log.error("taskExecute error:", e);
                    }
                }
            }
        }).start();
    }
}
