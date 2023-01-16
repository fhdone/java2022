package com.fhdone.java2022.july.job;

import com.fhdone.java2022.july.job.dto.TaskDetail;
import com.fhdone.java2022.july.job.service.TaskService;
import com.fhdone.java2022.july.job.service.impl.DemoTaskServiceImpl;
import com.fhdone.java2022.july.utils.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * @author fhdone
 */
@Slf4j
public class TaskRunner implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                TaskDetail taskDetail = TaskQueryRunner.getJOB_QUEUE().take();
                TaskService taskService = this.getTaskService(taskDetail);
                if(taskService==null){
                    throw new RuntimeException("taskService can not get");
                }
                boolean taskResult = taskService.runTask(taskDetail);
                if (taskResult) {
                    taskService.runTaskSuccess(taskDetail);
                } else {
                    taskService.runTaskFailed(taskDetail);
                }
            } catch (Exception e) {
                log.error("taskExecute error:", e);
            }
        }
    }

    private TaskService getTaskService(TaskDetail taskDetail) {
        String serviceId = taskDetail.getServiceId();
        switch (serviceId) {
            case DemoTaskServiceImpl.SERVICE_ID:
                log.debug("getTaskService: {}", DemoTaskServiceImpl.SERVICE_ID);
                return ApplicationContextUtil.getBean(DemoTaskServiceImpl.SERVICE_ID);
            default:
                //ignore
                log.error("getTaskService error, empty");
                return null;
        }
    }
}