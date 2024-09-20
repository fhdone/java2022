package com.fhdone.java2022.august.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.*;

@Slf4j
public class ReloadTest {

    private StopWatch stopWatch;

    @Test
    public void test() throws InterruptedException {
        stopWatch = new StopWatch();
        stopWatch.start();
        ExecutorService executor = new ThreadPoolExecutor(8, 32, 1, 
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(100));
        
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) throws Exception {
                        log.info("CacheLoader queryData");
                        return queryData(key);
                    }

                    @Override
                    public ListenableFuture<String> reload(Integer key, String oldValue) throws Exception {
                        log.info("CacheLoader reload");
                        ListenableFutureTask<String> task = ListenableFutureTask.create(() -> load(key + 1));
                        executor.execute(task);
                        return task;
                    }
                });
        
        Thread thread7 = startLoadingCacheQuery("client7", cache);
        Thread thread8 = startLoadingCacheQuery("client8", cache);
        thread7.join();
        thread8.join();
        Thread.sleep(3000);
        Thread thread9 = startLoadingCacheQuery("client9", cache);
        Thread thread10 = startLoadingCacheQuery("client10", cache);
        thread9.join();
        thread10.join();
        Thread.sleep(5000);
    }

    private String queryData(Integer key) throws InterruptedException {
        log("queryData start");
        Thread.sleep(3000);
        log("queryData end");
        return key.toString();
    }

    private Thread startLoadingCacheQuery(String clientName, LoadingCache<Integer, String> cache) {
        Thread thread = new Thread(() -> {
            log("get start");
            try {
                cache.get(1);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            log("get end");
        });
        thread.setName(clientName);
        thread.start();
        return thread;
    }

    private void log(String msg) {
        System.out.println(String.format("%ds %s %s", stopWatch.getTime() / 1000, Thread.currentThread().getName(), msg));
    }

}
