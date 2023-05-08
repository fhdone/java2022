package util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@Slf4j
public class MapTest {

    
    private static final int COUNT = 1000;
    private CountDownLatch countDownLatch = new CountDownLatch(COUNT);
    
    
    private Map<String, String> map = new HashMap<>();

    @BeforeEach
    public void before(){
        log.info("before");
        map.put("0","0");
    }

    @Test
    public void mapChange() throws Exception {
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    log.info(map.toString());
                    Assert.isTrue(map.size()==1);
                    if(countDownLatch.getCount()%50==0){
                        log.info("countDownLatch: {}", countDownLatch.getCount());
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<COUNT; i++){
                    Map<String, String> map2 = new HashMap<>();
                    map2.put(i+"", i+"" );
                    map = map2;
                    try {
                        TimeUnit.MILLISECONDS.sleep(20);
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        countDownLatch.await();
        
    }
    





}
