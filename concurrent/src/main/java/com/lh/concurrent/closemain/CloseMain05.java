package com.lh.concurrent.closemain;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseMain05 {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(4);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " 结束!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }

        try {
            countDownLatch.await();
            executorService.shutdown();
            if (!executorService.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
                executorService.shutdown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束!");
    }
}
