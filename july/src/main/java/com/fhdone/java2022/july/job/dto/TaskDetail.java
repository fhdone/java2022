package com.fhdone.java2022.july.job.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author fhdone
 */
@Data
@NoArgsConstructor
public class TaskDetail<T> {
    
    private String id;
    
    private String serviceId;
    
    private T detail;
    
    private String cron;
    
    private Date nextFireTime;


}
