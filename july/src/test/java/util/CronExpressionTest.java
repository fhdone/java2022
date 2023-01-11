package util;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.CronExpression;
import org.junit.jupiter.api.Test;

import java.util.Date;

@Slf4j
public class CronExpressionTest {

    @Test
    public void getTimeAfter() throws Exception {
        String cron = "0 15 10 ? * * ";
        CronExpression cronExpression = new CronExpression(cron);
        
        Date nextFiredDate = new Date();
        for(int i=0; i<5; i++) {
            nextFiredDate = cronExpression.getTimeAfter(nextFiredDate);
            log.info("next fire time:{}", nextFiredDate);
        }
    }

}
