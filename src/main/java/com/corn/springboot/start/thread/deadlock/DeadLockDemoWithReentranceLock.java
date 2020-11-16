package com.corn.springboot.start.thread.deadlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock trylock 无论是否拿到锁都立即响应，不会阻塞
 */
public class DeadLockDemoWithReentranceLock {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();
        ThreadOne threadOne = new ThreadOne(lock1,lock2);
        ThreadTwo threadTwo = new ThreadTwo(lock1,lock2);

        Thread t1 = new Thread(threadOne);
        Thread t2 = new Thread(threadTwo);
        t1.start();
        t2.start();
        Thread.sleep(3000);
        t1.interrupt();
    }


}

class ThreadOne implements Runnable{

    private ReentrantLock object1;

    private ReentrantLock object2;

    public ThreadOne(ReentrantLock object1,ReentrantLock object2){
        this.object1 = object1;
        this.object2 = object2;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":"+"try to get object1 lock");
        if(object1.tryLock()){
            try{
                Thread.sleep(50000);
                System.out.println(Thread.currentThread().getName()+":"+"get object1 lock");
                System.out.println(Thread.currentThread().getName()+":"+"try to get object2 lock");
                if(object2.tryLock()){
                    try{
                        System.out.println(Thread.currentThread().getName()+":"+"get object2 lock");
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        object2.unlock();
                        System.out.println(Thread.currentThread().getName()+":"+"released lock2");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                object1.unlock();
                System.out.println(Thread.currentThread().getName()+":"+"released lock1");
            }
        }
    }
}

class ThreadTwo implements Runnable{

    private ReentrantLock object1;

    private ReentrantLock object2;

    public ThreadTwo(ReentrantLock object1,ReentrantLock object2){
        this.object1 = object1;
        this.object2 = object2;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":"+"try to get object2 lock");
        if(object2.tryLock()){
            try{
                Thread.sleep(50000);
                System.out.println(Thread.currentThread().getName()+":"+"get object2 lock");
                System.out.println(Thread.currentThread().getName()+":"+"try to get object1 lock");
                if(object1.tryLock()){
                    try{
                        System.out.println(Thread.currentThread().getName()+":"+"get object1 lock");
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        object1.unlock();
                        System.out.println(Thread.currentThread().getName()+":"+"released lock1");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                object2.unlock();
                System.out.println(Thread.currentThread().getName()+":"+"released object2");
            }
        }
    }
}