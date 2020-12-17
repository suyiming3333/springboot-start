package com.corn.springboot.start.thread.threadlocal;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ThreadLocalDemo {


    private String string;

    public Integer getMyInt() {
        return integerThreadLocal.get();
    }

    public void setMyInt(Integer myInt) {
        integerThreadLocal.set(myInt);
    }

    public void setMap(String key,Integer value) {
        mapThreadLocal.set(Collections.singletonMap(key,value));
    }

    private Integer myInt;

    private String getString() {
        return stringThreadLocal.get();
//        return string;
    }

    private void setString(String string) {
        stringThreadLocal.set(string);
//        this.string = string;
    }

    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Map<String,Integer>> mapThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        int threads = 9;
        ThreadLocalDemo demo = new ThreadLocalDemo();
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        //启动多个线程，每个线程设定自己的名字，然后输出。threadname(各自线程输出自己的名字)
        //普通地设置string变量，在多线程下会读不到自己正确的线程名称
        //使用Threadlocal设置变量，可以取到正确的名字，使变量实现线程隔离
        for (int i = 0; i < threads; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                demo.setString(Thread.currentThread().getName());
                demo.setMyInt(finalI);
                demo.setMap(Thread.currentThread().getName(),finalI);
                try {
                    Thread.sleep((int)(Math.random()*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("期待输出的线程名字"+Thread.currentThread().getName()+"实际输出的名字"+demo.getString());
                countDownLatch.countDown();
            }, "thread - " + i);
            thread.start();
        }
    }

}
