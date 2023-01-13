package com.fhdone.java2022.july.job.service;

import lombok.extern.slf4j.Slf4j;

public interface TaskService {
    
    public boolean runTaskSuccess(Object obj);
    
    public boolean runTaskFailed(Object obj);
    
    public boolean runTask(Object obj);
    
}
