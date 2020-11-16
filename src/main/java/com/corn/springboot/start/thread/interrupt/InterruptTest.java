package com.corn.springboot.start.thread.interrupt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        System.out.println(LocalDateTime.now());
                    }
                } catch (InterruptedException e) {
                    System.out.println("中断标志：" + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(LocalDateTime.now());
                    } catch (InterruptedException e) {
                        System.out.println("中断标志：" + Thread.currentThread().isInterrupted());
                        //响应中断后，isInterrupted标志会重置，需要再一次设置中断标志位
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }

                    //响应中断，终止循环
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("终止循环");
                        break;
                    }
                }

            }
        });

        t2.start();
        Thread.sleep(4000);
        t2.interrupt();
    }
}
