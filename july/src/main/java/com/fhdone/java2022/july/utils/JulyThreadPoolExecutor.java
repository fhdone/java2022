package com.fhdone.java2022.july.utils;

import com.fhdone.java2022.july.job.TaskRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


@Slf4j
public class JulyThreadPoolExecutor extends ThreadPoolExecutor {


    public JulyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                  long keepAliveTime, TimeUnit unit,
                                  BlockingQueue<Runnable> workQueue,
                                  ThreadFactory threadFactory) {

        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        
        log.info("beforeExecute");
        if(r instanceof TaskRunner){
            log.info("beforeExecute is TaskRunner");
        }
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        
        log.info("afterExecute");
        if(r instanceof TaskRunner){
            log.info("beforeExecute is TaskRunner");
        }
        super.afterExecute(r, t);
    }
}
