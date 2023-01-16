package com.fhdone.java2022.july.utils;

import com.fhdone.java2022.july.job.dto.TaskDetail;
import com.fhdone.java2022.july.job.service.impl.DemoTaskServiceImpl;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fhdone
 */
public class TaskUtils {
    
    private static final String MOCK_JOB_CRON = "0/5 * * * * ?";
    
    public static TaskDetail<Map<String, Object>> getMockJobDetail(){
        String jobString = RandomStringUtils.random(20, true, true);
        TaskDetail<Map<String, Object>> taskDetail = new TaskDetail<>();
        taskDetail.setId(jobString);
        taskDetail.setCron(MOCK_JOB_CRON);
        taskDetail.setServiceId(DemoTaskServiceImpl.SERVICE_ID);
        taskDetail.setNextFireTime(new Date());
        
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("jobString",jobString);
        taskDetail.setDetail(detailMap);
        
        return taskDetail;
    }
    
    
}
