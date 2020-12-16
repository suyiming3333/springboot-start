package com.corn.springboot.start.thread.excutorservice;

import java.util.concurrent.*;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ExecutorCompletionServiceDemo
 * @Package com.corn.springboot.start.thread.excutorservice
 * @Description: TODO
 * @date 2020/12/16 13:16
 */
public class ExecutorCompletionServiceDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService<String>(executorService);
        long startTime = System.currentTimeMillis();

        for(int i=0;i<50;i++){
            executorCompletionService.submit(new SleepTimeCallableTask(i));
        }

        int count = 0;
        while(count<50){
            //凡是能take的future 一定是完成的future(如果没有完成的任务take会阻塞)
            Future future = executorCompletionService.take();
            System.out.println(System.currentTimeMillis());
            if(future.isDone()){
                System.out.println("有任务完成了"+future.get());
                System.out.println(System.currentTimeMillis());
                count++;
            }
        }

        long endTime = System.currentTimeMillis();
        long total = endTime - startTime;
        System.out.println("所有任务完成了,共耗时："+total+"ms");
        executorService.shutdown();
    }
}
