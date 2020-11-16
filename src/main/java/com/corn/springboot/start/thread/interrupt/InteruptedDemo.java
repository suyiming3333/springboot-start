package com.corn.springboot.start.thread.interrupt;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class InteruptedDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            ReentrantLock lock = new ReentrantLock();
            @Override
            public void run() {
                try {
                    lock.lock();
                    while(true){
                        Thread.sleep(1000);
                        System.out.println(LocalDateTime.now());
                    }
//                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    System.out.println("中断标志位："+Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                    System.out.println("中断标志位："+Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });
        t.start();
        Thread.sleep(5000);
        t.interrupt();//中断
    }
}
