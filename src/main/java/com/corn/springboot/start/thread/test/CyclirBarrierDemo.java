package com.corn.springboot.start.thread.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclirBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,()->{
            System.out.println("最后一个人是"+Thread.currentThread().getName()+"也到了，全体出发");
        });

        for(int i = 0;i<10;i++){
            Thread thread = new Thread(new AssembleThread("员工"+i,cyclicBarrier));
            thread.setName("员工"+i);
            thread.start();
            if(i == 8){
                thread.interrupt();
            }
        }
    }
}


class AssembleThread implements Runnable{

    private String userName;

    private CyclicBarrier cyclicBarrier;

    public AssembleThread(String userName,CyclicBarrier cyclicBarrier){
        this.userName = userName;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {

        System.out.println(userName+"起床赶去广电广场集中.....");
        try {
            if(userName.equals("员工10")){
                Thread.sleep((int) (Math.random() * 1000));
            }
            System.out.println(userName+"到广电广场集中了，等其他人中");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName()+"好，出发了");
//            if(userName.equals("员工8")){
//                cyclicBarrier.await(100, TimeUnit.NANOSECONDS);
//            }else{
//                cyclicBarrier.await();
//            }
        } catch (InterruptedException e) {
            System.out.println("中断标志"+Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().getName()+"有事先先出发了");
            System.out.println("中断标志"+Thread.currentThread().isInterrupted());
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println(Thread.currentThread().getName()+"也先出发了");
            e.printStackTrace();
        }
//        catch (TimeoutException e) {
//            System.out.println("中断标志"+Thread.currentThread().isInterrupted());
//            System.out.println(userName+"等不下去了，滚");
//        }
    }
}
