package com.corn.springboot.start.thread.test;

public class ThreadCooperationWithSynchronized {

    private static Object lock = new Object();

    private static volatile boolean flag = true;


    public static void main(String[] args) {
        Thread numbericThread = new Thread(()->{
            int i = 1;
            synchronized (lock){
                while(true){
                    if(flag){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(i++);
                        lock.notify();
                        flag = !flag;
                    }else{
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread letterThread = new Thread(()->{
            char a = 'A';
            synchronized (lock){
                while(true){
                    if(flag){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(a++);
                        if(a == 'Z'+1){
                            a = 'A';
                        }
                        lock.notify();
                        flag = !flag;
                    }
                }
            }
        });

        numbericThread.start();
        letterThread.start();
    }


}
