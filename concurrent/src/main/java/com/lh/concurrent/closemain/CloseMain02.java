package com.lh.concurrent.closemain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseMain02 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i ++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " 结束!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        // 使用shutdown 来通知executorService 停止当前的task
        executorService.shutdown();
        if (!executorService.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
            executorService.shutdown();
        }
        System.out.println("主线程结束！");
    }
}
