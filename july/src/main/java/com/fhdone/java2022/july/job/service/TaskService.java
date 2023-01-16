package com.fhdone.java2022.july.job.service;

import com.fhdone.java2022.july.job.dto.TaskDetail;

/**
 * task service
 * @author fhdone
 */
public interface TaskService<E> {
    
    public boolean runTaskSuccess(TaskDetail<E> taskDetail);
    
    public boolean runTaskFailed(TaskDetail<E> taskDetail);
    
    public boolean runTask(TaskDetail<E> taskDetail);
    
}
