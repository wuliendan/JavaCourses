package com.lh.concurrent.closemain;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CloseMain08 {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("回调 >> " + Thread.currentThread().getName());
                System.out.println("回调 >> 线程组执行结束");
            }
        });

        for (int i = 0; i < 5; i++) {
           new Thread(new readNum(i, cyclicBarrier)).start();
        }

        System.out.println("===> 各个子线程执行结束...");
        System.out.println("===> 主线程执行结束...");
    }

    static class readNum implements Runnable {
        private int id;
        private CyclicBarrier cyclicBarrier;

        public readNum(int id, CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            synchronized (this) {
                System.out.println("id: " + id + "," + Thread.currentThread().getName());
                try {
                    System.out.println("线程组任务 " + id + " 结束，算他任务继续");
                    cyclicBarrier.await();  //子线程await
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}