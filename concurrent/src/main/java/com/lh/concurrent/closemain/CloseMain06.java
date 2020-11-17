package com.lh.concurrent.closemain;

import java.util.concurrent.*;

public class CloseMain06 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<Integer> task1 = executorService.submit(() -> {
            new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " 结束!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            return 2;
        });

        Future<Integer> task2 = executorService.submit(() -> {
            new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        System.out.println(Thread.currentThread().getName() + " 结束!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            return 3;
        });

        executorService.shutdown();
        if (!executorService.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
            executorService.shutdown();
        }

        System.out.println("task1的运行结果:" + task1.get());
        System.out.println("task2的运行结果:" + task2.get());
        System.out.println("主线程结束");
    }
}
