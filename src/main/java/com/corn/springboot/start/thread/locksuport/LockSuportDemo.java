package com.corn.springboot.start.thread.locksuport;

import java.time.LocalDateTime;
import java.util.concurrent.locks.LockSupport;

public class LockSuportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            try{
                Thread.sleep(1000);
                System.out.println("lock park");
                LockSupport.park();
                while(true){
                    Thread.sleep(1000);
                    System.out.println(LocalDateTime.now());
                }
            }catch (InterruptedException e){
                System.out.println("中断标志："+Thread.currentThread().isInterrupted());
            }finally {

            }

        });

        t.start();
        Thread.sleep(2000);
        LockSupport.unpark(t);
        System.out.println("unpark 通知线程启动");
        Thread.sleep(10000);
        Thread.interrupted();
    }
}
