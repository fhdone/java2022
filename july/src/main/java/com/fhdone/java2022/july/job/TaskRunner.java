package com.fhdone.java2022.july.job;

import com.fhdone.java2022.july.job.dto.JobDetail;
import com.fhdone.java2022.july.job.service.TaskService;
import com.fhdone.java2022.july.job.service.impl.DemoTaskServiceImpl;
import com.fhdone.java2022.july.utils.ApplicationContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Slf4j
public class TaskRunner implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                JobDetail jobDetail = TaskQueryRunner.getJOB_QUEUE().take();
                TaskService taskService = this.getTaskService(jobDetail);
                boolean taskResult = taskService.runTask(jobDetail);
                if (taskResult) {
                    taskService.runTaskSuccess(jobDetail);
                } else {
                    taskService.runTaskFailed(jobDetail);
                }
            } catch (Exception e) {
                log.error("taskExecute error:", e);
            }
        }
    }

    private TaskService getTaskService(JobDetail jobDetail) {
        String serviceId = jobDetail.getServiceId();
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