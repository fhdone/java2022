package com.fhdone.java2022.august.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Slf4j
public class ThreadLocalDemo {

//     private static ThreadLocal<String> ttl = new ThreadLocal<>();
//      private static ThreadLocal<String> ttl = new InheritableThreadLocal<>();
      
    private static ThreadLocal<String> ttl = new TransmittableThreadLocal<>();
   

    /** 保证只有1个线程,以便观察这个线程被多个Runnable复用时，能否成功完成ThreadLocal的传递 **/
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1,1,0,TimeUnit.SECONDS,new ArrayBlockingQueue<>(10)
    );

    @Test
    public void test() throws Exception {
        ttl.set("yogurtzzz");

        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                ttl.set("changed");
            }
            TtlRunnable runnable = TtlRunnable.get(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + ttl.get());
            });

            threadPoolExecutor.execute(runnable);
            //CompletableFuture.runAsync(runnable,threadPoolExecutor);
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

}
