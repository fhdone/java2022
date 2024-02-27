package com.fhdone.java2022.august.utils;

import org.junit.jupiter.api.Test;
import java.util.concurrent.locks.ReentrantLock;


/**
 * https://pjmike.github.io/2019/04/01/%E6%B5%85%E6%9E%90-ReentrantLock/
 */
public class ReenterLockDemo {

    public static int i = 0;

    @Test
    public void test() throws Exception {
        ReenterLock reenterLock = new ReenterLock();
        Thread t1 = new Thread(reenterLock);
        Thread t2 = new Thread(reenterLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }


    public class ReenterLock implements Runnable{
        public static ReentrantLock reentrantLock = new ReentrantLock();

        public void run() {
            for (int j = 0;j<100000;j++) {
                reentrantLock.lock();
                //reentrantLock.lock();
                try {
                    i++;
                }finally {
                    reentrantLock.unlock();
                    //reentrantLock.unlock();
                }
            }
        }

    }

}


