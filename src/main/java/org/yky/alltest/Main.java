package org.yky.alltest;


import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by lenovo on 2018/12/4.
 */
public class Main {

    private static AtomicInteger echoRetries = new AtomicInteger(1);


    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadFactory() {

            private final AtomicInteger threadNumber = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "nonpmtasyncThread-" + threadNumber.getAndIncrement());
            }
        }, (r, executor) -> {
            System.out.println("非业务线程池已满，将会拒绝线程");
            // todo 告警
        });
        Runnable runnable = () -> {
            try {
                System.out.println("线程进来了");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);


        Thread.sleep(2000);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);

    }

    static class Test {
        public void aa() {

        }
    }

}
