package com.fhdone.java2022.july.job.service;

import com.fhdone.java2022.july.job.dto.TaskDetail;

/**
 * task service
 * @author fhdone
 */
public interface TaskService<T> {
    
    boolean runTaskSuccess(TaskDetail<T> taskDetail);
    
    boolean runTaskFailed(TaskDetail<T> taskDetail);
    
    boolean runTask(TaskDetail<T> taskDetail);
    
}
