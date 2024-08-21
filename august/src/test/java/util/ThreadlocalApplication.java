package util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

 @Slf4j
public class ThreadlocalApplication {

    public static final ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();
    public static final ExecutorService exec = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 1000; ++i) {
            
            log.info(i+"");
            //test1();
            //test2(i);
        }
    }

    private static void test1() {
        exec.execute(() -> {
            threadLocal.set(new byte[1024 * 1024 ]);
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //threadLocal.remove();
            }
        });
    }

    private static void test2(int i) {
        System.out.println(i);
        Thread t =  new Thread(){
            @SneakyThrows
            public void run() {
                threadLocal.set(new byte[1024 * 1024]);
                TimeUnit.MILLISECONDS.sleep(50);
            }
        };
        t.run();
    }

}
