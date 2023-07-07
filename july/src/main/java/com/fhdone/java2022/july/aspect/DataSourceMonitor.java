package com.fhdone.java2022.july.aspect;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 连接池监控
 */
//@Component
@EnableScheduling
@Slf4j
public class DataSourceMonitor {

    private HikariPoolMXBean proxy;

    @Resource
    private HikariDataSource hikariDataSource;

    
    @Scheduled(cron = "0/5 * * * * ?")
    public void monitor() {
        printHikariDataSource();
    }


    private void printHikariDataSource() {
        try {
            if (null == proxy) {
                MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
                ObjectName poolName = new ObjectName("com.zaxxer.hikari:type=Pool (" + hikariDataSource.getPoolName() + ")");
                proxy = JMX.newMXBeanProxy(mBeanServer, poolName, HikariPoolMXBean.class);
            } else {
               // if (proxy.getActiveConnections() > 0) {
                    log.info("HikariPoolState = "
                        + "Active=[" + String.valueOf(proxy.getActiveConnections() + "] "
                        + "Idle=[" + String.valueOf(proxy.getIdleConnections() + "] "
                        + "Wait=[" + proxy.getThreadsAwaitingConnection() + "] "
                        + "Total=[" + proxy.getTotalConnections() + "]")));
               // }
            }
        } catch (Exception e) {
            log.error("fail》》", e);
        }
    }
}
