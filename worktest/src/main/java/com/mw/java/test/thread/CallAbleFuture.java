package com.mw.java.test.thread;

import java.util.concurrent.*;

/**
 * Created by mawei on 2016/6/28.
 */
public class CallAbleFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "hello thread";
            }
        });
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
