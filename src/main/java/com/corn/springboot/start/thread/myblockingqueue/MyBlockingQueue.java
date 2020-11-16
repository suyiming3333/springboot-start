package com.corn.springboot.start.thread.myblockingqueue;

import java.util.Date;
import java.util.LinkedList;

public class MyBlockingQueue {

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

class ProducerThread<T> implements Runnable{

    private MyQueue<T> myQueue;

    public ProducerThread(MyQueue<T> myQueue){
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        while(true){
            Date date = new Date();
            try {
                myQueue.put((T) date);
                System.out.println("size after put:"+myQueue.getSize());
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConsumerThread<T> implements Runnable{

    private MyQueue<T> myQueue;

    public ConsumerThread(MyQueue<T> myQueue){
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                T take = myQueue.take();
                System.out.println("消费了一个元素："+(T)take);
                System.out.println("size after take:"+myQueue.getSize());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyQueueBak<T>{
    private Integer maxSize = 10;

    private LinkedList<T> storage = new LinkedList<T>();

    public synchronized int getSize(){
        return storage.size();
    }

    public synchronized void put(T t) throws InterruptedException {
        //队列放满了，阻塞
        while(storage.size() == maxSize){
            System.out.println("队列放满了");
            this.wait();
        }
        storage.add(t);
        //若果消费者元素消费完了，放入元素后，通知消费者可以消费
        this.notify();
        System.out.println("放入了元素："+t.toString());
    }

    public synchronized T take() throws InterruptedException {
        //取完了，阻塞
        while(storage.size() == 0){
            System.out.println("队列取空了");
            this.wait();
        }
        T poll = storage.poll();
        //如果生产者生产满了，取出一个元素后就通知生产者生产
        this.notify();
        System.out.println("取出了元素:"+poll.toString());
        return poll;
    }
}
