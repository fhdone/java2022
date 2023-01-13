package com.fhdone.java2022.july.job.service;

import lombok.extern.slf4j.Slf4j;

/**
 * task service
 */
public interface TaskService<JobDetail> {
    
    public boolean runTaskSuccess(JobDetail jobDetail);
    
    public boolean runTaskFailed(JobDetail jobDetail);
    
    public boolean runTask(JobDetail jobDetail);
    
}
