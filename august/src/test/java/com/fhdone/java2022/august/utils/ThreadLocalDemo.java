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
//     private static ThreadLocal<String> ttl = new InheritableThreadLocal<>();
    private static ThreadLocal<String> ttl = new TransmittableThreadLocal<>();


    @Test
    public void test() throws Exception {

        /** 保证只有1个线程,以便观察这个线程被多个Runnable复用时，能否成功完成ThreadLocal的传递 **/
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,1,0,TimeUnit.SECONDS,new ArrayBlockingQueue<>(10)
        );

        ttl.set("raw info");

        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                ttl.set("raw info has changed");
            }
            TtlRunnable runnable = TtlRunnable.get(() -> {
                log.info(Thread.currentThread().getName() + " : " + ttl.get());
            });

            threadPoolExecutor.execute(runnable);

//            CompletableFuture<Void> future = CompletableFuture.runAsync(runnable,threadPoolExecutor);
//            future.whenComplete(new BiConsumer<Void, Throwable>() {
//                @Override
//                public void accept(Void t, Throwable action) {
//                    log.info("执行完成！");
//                }
//
//            });
//            future.exceptionally(new Function<Throwable, Void>() {
//                @Override
//                public Void apply(Throwable t) {
//                    log.info("执行失败！"+t.getMessage());
//                    return null;
//                }
//            });

            TimeUnit.MILLISECONDS.sleep(500);
        }
    }




}
