package util;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.CronExpression;
import org.junit.jupiter.api.Test;

import java.util.Date;

@Slf4j
public class CronExpressionTest {

    @Test
    public void getTimeAfter() throws Exception {
        log.info("##### 0 15 10 ? * *  #####");
        this.calcTimeAfter("0 15 10 ? * * ");
        
        log.info("##### 0 0 12 ? * WED #####");
        this.calcTimeAfter("0 0 12 ? * WED ");
    }
    
    
    
    public void calcTimeAfter(String cronStr) throws Exception {
        CronExpression cronExpression = new CronExpression(cronStr);
        
        Date nextFiredDate = new Date();
        for(int i=0; i<5; i++) {
            nextFiredDate = cronExpression.getTimeAfter(nextFiredDate);
            log.info("next fire time:{}", nextFiredDate);
        }
    }

}
