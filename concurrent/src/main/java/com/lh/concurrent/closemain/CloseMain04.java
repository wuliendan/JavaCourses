package com.lh.concurrent.closemain;

public class CloseMain04 {

    public static void main(String[] args) {
        Thread.currentThread().getThreadGroup().list();

        int defaultThreadNum = 2;
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " 结束!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        while (Thread.activeCount() > defaultThreadNum) {
            // 当活跃线程大于设定的默认线程数时，主线程让步
            Thread.yield();
        }

        System.out.println("主线程结束!");
    }
}
