package com.corn.springboot.start.thread.excutorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ExcutorServiceDemo
 * @Package com.corn.springboot.start.thread.excutorservice
 * @Description: TODO
 * @date 2020/12/16 12:38
 */
public class ExcutorServiceDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Future<String>> futureList = new ArrayList<>();
        int count = 0;
        long startTime = System.currentTimeMillis();
        for(int i=0;i<50;i++){
            futureList.add(executorService.submit(new SleepTimeCallableTask(i)));
        }

        //自旋，所有任务都要执行完
        while(count<50){
            for(int j=0;j<futureList.size();j++){
                Future<String> future = futureList.get(j);
                if(future.isDone()){
                    count++;
                    System.out.println((String)future.get());
                    System.out.println(count+"个任务完成了");
                    futureList.remove(j);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long total = endTime - startTime;
        System.out.println("所有任务完成了,共耗时："+total+"ms");
        executorService.shutdown();
    }

}

class SleepTimeCallableTask implements Callable<String>{

    private int i;

    public SleepTimeCallableTask(int i){
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        String threadName = Thread.currentThread().getName()+":"+i;
        System.out.println(threadName+" run");
        int sleepTime =  (int)(Math.random() * 10000);
        Thread.sleep(sleepTime);
        return threadName +"运行了" + String.valueOf(sleepTime) +"ms";
    }
}



