package com.fhdone.java2022.july.utils;

import com.fhdone.java2022.july.job.dto.TaskDetail;
import com.fhdone.java2022.july.job.service.impl.Demo2TaskServiceImpl;
import com.fhdone.java2022.july.job.service.impl.DemoTaskServiceImpl;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fhdone
 */
public class TaskUtils {

    private static final String MOCK_JOB_CRON_5 = "0/5 * * * * ?";
    private static final String MOCK_JOB_CRON_10 = "0/10 * * * * ?";

    public static TaskDetail<Map<String, Object>> getMockJobDetail(){
        String jobString = RandomStringUtils.random(20, true, true);
        TaskDetail<Map<String, Object>> taskDetail = new TaskDetail<>();
        taskDetail.setId(jobString);
        taskDetail.setCron(MOCK_JOB_CRON_5);
        taskDetail.setServiceId(DemoTaskServiceImpl.SERVICE_ID);
        taskDetail.setNextFireTime(new Date());

        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("jobString",jobString);
        taskDetail.setDetail(detailMap);
        return taskDetail;
    }



    public static TaskDetail<String> getMockJobDetail2(){
        String jobString = RandomStringUtils.random(40, true, true);
        TaskDetail<String> taskDetail = new TaskDetail<>();
        taskDetail.setId(jobString);
        taskDetail.setCron(MOCK_JOB_CRON_10);
        taskDetail.setServiceId(Demo2TaskServiceImpl.SERVICE_ID);
        taskDetail.setNextFireTime(new Date());
        taskDetail.setDetail(jobString);
        return taskDetail;
    }



}
