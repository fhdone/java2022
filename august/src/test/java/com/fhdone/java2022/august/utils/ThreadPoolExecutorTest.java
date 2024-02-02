package com.fhdone.java2022.august.utils;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * https://mp.weixin.qq.com/s/0R4Eiu4KT4-RUV2MdJif4g
 */
public class ThreadPoolExecutorTest {

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(64,
            64, 0, TimeUnit.MINUTES, new ArrayBlockingQueue<>(32)) {
        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            mySleep(100);
        }
    };

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 400; i++) {
            list.add(i);
        }
        for (int i = 0; i < 100; i++) {
            List<List<Integer>> sublist = Lists.partition(list, 400 / 32);
            int n = sublist.size();
            CountDownLatch countDownLatch = new CountDownLatch(n);
            for (int j = 0; j < n; j++) {
//                while (threadPoolExecutor.getQueue().remainingCapacity() == 0) {
//                    System.out.println("线程池不够用了");
//                    mySleep(100);
//                }
                threadPoolExecutor.execute(() -> {
                    mySleep(100);
                    countDownLatch.countDown();
                });
            }
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("===============>  详情任务 - 任务处理完成");
        }
        System.out.println("都执行完成了");
    }

    private static void mySleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
    
