package com.fhdone.java2022.july.job;

import com.fhdone.java2022.july.job.dto.JobDetail;
import com.fhdone.java2022.july.job.service.TaskService;
import com.fhdone.java2022.july.job.service.impl.DemoTaskServiceImpl;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.*;

@Component
@Slf4j
public class TaskExecuteJob {

    private static int CORE_POOL_SIZE = 2;
    private static int MAX_POOL_SIZE = 2;

    @Resource(name = DemoTaskServiceImpl.SERVICE_ID)
    private TaskService demoTaskService;

    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
        .setNameFormat("TASK-EXECUTE-JOB-POOL-%d").build();

    private Executor taskExecutor =  new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
        0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(), namedThreadFactory);

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
                    JobDetail jobDetail = TaskQueryRunner.getJOB_QUEUE().take();
                    boolean taskResult = demoTaskService.runTask(jobDetail);
                    if(taskResult){
                        demoTaskService.runTaskSuccess(jobDetail);
                    }else{
                        demoTaskService.runTaskFailed(jobDetail);
                    }
                } catch (Exception e) {
                    log.error("taskExecute error:", e);
                }
            }
        }
    }


}
