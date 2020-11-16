package com.corn.springboot.start.thread.test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureDemo {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        try{
            for(int i = 0 ;i<5;i++){
                executorService.submit(new Counter(i,executorService));
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            executorService.shutdownNow();
        }
    }
}

class Counter implements Runnable{

    private Integer index;

    private ExecutorService executorService;

    public Counter(Integer index,ExecutorService executorService){
        this.index = index;
        this.executorService = executorService;
    }

    @Override
    public void run() throws RuntimeException{
        try {
            Thread.sleep((int) (Math.random() * 1000));
            if(index == 3){
                int o = index/0;
            }else{
//                Thread.sleep(1000);
                System.out.println("bunisses exec:"+index);
            }
        } catch (InterruptedException e) {
            System.out.println("线程被中断了："+Thread.currentThread().isInterrupted());
            e.printStackTrace();
        } catch (ArithmeticException e){
            executorService.shutdownNow();
            System.out.println(1);
        }

    }
}
