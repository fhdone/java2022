package com.fhdone.java2022.july.job;

import com.fhdone.java2022.july.utils.JulyThreadPoolExecutor;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
@Slf4j
public class TaskExecuteJob {

    private static final int CORE_POOL_SIZE = 2;
    private static final int MAX_POOL_SIZE = 2;
    
    private final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("TASK-EXECUTE-JOB-POOL-%d").build();

    private final Executor taskExecutor =  new JulyThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), namedThreadFactory);

    @PostConstruct
    public void taskExecuteRunner() {
        log.info("taskExecuteRunner start");
        taskExecutor.execute(new TaskRunner());
        taskExecutor.execute(new TaskRunner());
    }


}
