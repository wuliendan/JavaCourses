package com.lh.concurrent.concurrent.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Thread01 {

    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Thread t = Thread.currentThread();
                log.info("当前线程：{}", t.getName());
            }
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        thread.setDaemon(false);
        thread.start();
    }
}
