package com.lh.concurrent.closemain;

public class CloseMain01 {

  public static void main(String[] args) throws InterruptedException {
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
      Thread.sleep(2000);
      System.out.println("主线程结束!");
  }
}
