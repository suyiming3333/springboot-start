package com.corn.springboot.start.thread.test;

import java.util.concurrent.locks.LockSupport;

public class ThreadCooperationWithLockSuport {

    private static volatile boolean flag = true;

    public static void main(String[] args) {
        Thread numbericThread = null;
        Thread letterThread = null;

        numbericThread = new Thread(new NumericThread(flag,letterThread));
        letterThread = new Thread(new LetterThread(flag,numbericThread));

        numbericThread.start();
        letterThread.start();

    }
}

class LetterThread implements Runnable{
    private boolean flag;

    private Thread otherThread;

    public LetterThread(boolean flag,Thread otherThread){
        this.flag = flag;
        this.otherThread = otherThread;
    }

    @Override
    public void run() {
        char a = 'A';
        while(true){
            if(flag){
                LockSupport.park();
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
                LockSupport.unpark(otherThread);
                flag = !flag;
            }
        }
    }
}

class NumericThread implements Runnable{

    private boolean flag;

    private Thread otherThread;

    public NumericThread(boolean flag,Thread otherThread){
        this.flag = flag;
        this.otherThread = otherThread;
    }

    @Override
    public void run() {
        int i = 1;
        while(true){
            if(flag){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i++);
                LockSupport.unpark(otherThread);
                flag = !flag;
            }else{
                LockSupport.park();
            }
        }
    }
}
