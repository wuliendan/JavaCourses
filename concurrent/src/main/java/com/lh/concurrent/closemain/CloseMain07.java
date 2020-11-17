package com.lh.concurrent.closemain;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CloseMain07 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
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

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
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
        }).thenCombine(completableFuture1, (result1, result2) -> result1 * result2);

        System.out.println("completableFuture2的运行结果: " + completableFuture2.get());
        System.out.println("主线程结束!");
    }
}
