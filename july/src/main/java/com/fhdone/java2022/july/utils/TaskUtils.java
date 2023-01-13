package com.fhdone.java2022.july.utils;

import com.fhdone.java2022.july.job.dto.JobDetail;
import com.fhdone.java2022.july.job.service.impl.DemoTaskServiceImpl;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskUtils {
    
    private static final String MOCK_JOB_CRON = "0/5 * * * * ?";
    
    public static JobDetail<Map<String, Object>> getMockJobDetail(){
        String jobString = RandomStringUtils.random(20, true, true);
        JobDetail<Map<String, Object>> jobDetail = new JobDetail();
        jobDetail.setId(jobString);
        jobDetail.setCron(MOCK_JOB_CRON);
        jobDetail.setServiceId(DemoTaskServiceImpl.SERVICE_ID);
        jobDetail.setNextFireTime(new Date());
        
        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("jobString",jobString);
        jobDetail.setDetail(detailMap);
        
        return jobDetail;
    }
    
    
}
