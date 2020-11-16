package com.corn.springboot.start.thread.deadlock;

/**
 * synchronized 阻塞，不可中断
 */
public class DeadLockDemo {

    public static void main(String[] args) {
//        Object lock1 = new Object();
//        Object lock2 = new Object();
//        ThreadOne threadOne = new ThreadOne(lock1,lock2);
//        ThreadTwo threadTwo = new ThreadTwo(lock1,lock2);
//
//        Thread t1 = new Thread(threadOne);
//        Thread t2 = new Thread(threadTwo);
//        t1.start();
//        t2.start();
    }


}

class ThreadOnebak implements Runnable{

    private Object object1;

    private Object object2;

    public ThreadOnebak(Object object1,Object object2){
        this.object1 = object1;
        this.object2 = object2;
    }

    @Override
    public void run() {
        System.out.println("try to get object1 lock");
        synchronized (object1){
            System.out.println("get object1 lock");
            System.out.println("try to get object2 lock");
            synchronized (object2){
                System.out.println("get object2 lock");
            }
        }
    }
}

class ThreadTwobak implements Runnable{

    private Object object1;

    private Object object2;

    public ThreadTwobak(Object object1,Object object2){
        this.object1 = object1;
        this.object2 = object2;
    }

    @Override
    public void run() {
        System.out.println("try to get object2 lock");
        synchronized (object2){
            System.out.println("get object2 lock");
            System.out.println("try to get object1 lock");
            synchronized (object1){
                System.out.println("get object1 lock");
            }
        }
    }
}