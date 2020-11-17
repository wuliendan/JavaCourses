package com.lh.concurrent.closemain;

import java.util.ArrayList;
import java.util.List;

public class CloseMain03 {

    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread("子线程 " + i) {
               @Override
                public void run() {
                   try {
                       Thread.sleep(3000);
                       System.out.println(Thread.currentThread().getName() + " 结束!");
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            };
            list.add(thread);
            thread.start();
        }

        for (Thread item : list) {
            item.join();
        }

        System.out.println("主线程结束!");
    }
}
