package com.corn.springboot.start.thread.myblockingqueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue1 {

    public static void main(String[] args) throws InterruptedException {
        MyQueue<Integer> queue = new MyQueue<>();
        ProducerThread producerThread = new ProducerThread(queue);
        ConsumerThread consumerThread = new ConsumerThread(queue);
        Thread threadp = new Thread(producerThread);
        Thread threadc = new Thread(consumerThread);

        threadp.start();
        threadc.start();

        threadp.join();
        threadc.join();

        System.out.println("main end");

    }
}


class MyQueue<T>{

    private ReentrantLock reentrantLock = new ReentrantLock(false);

    private Condition condition = reentrantLock.newCondition();

    private Integer maxSize = 10;

    private LinkedList<T> storage = new LinkedList<T>();

    public int getSize(){
        return storage.size();
    }

    public void put(T t) throws InterruptedException {
        reentrantLock.lock();
        try{
            //队列放满了，阻塞
            while(storage.size() == maxSize){
                System.out.println("队列放满了");
                condition.await();
            }
            storage.add(t);
            //若果消费者元素消费完了，放入元素后，通知消费者可以消费
            condition.signal();
            System.out.println("放入了元素："+t.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }


    }

    public T take() throws InterruptedException {
        T poll;
        reentrantLock.lock();
        try{
            //取完了，阻塞
            while(storage.size() == 0){
                System.out.println("队列取空了");
                condition.await();
            }
            poll = storage.poll();
            //如果生产者生产满了，取出一个元素后就通知生产者生产
            condition.signal();
            System.out.println("取出了元素:"+poll.toString());
            return poll;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
        return null;
    }
}
