package com.corn.springboot.start.thread.test;

import java.util.concurrent.*;

/**
 * 1、生产每份奶酪需要2份牛奶和一份发酵剂
 * 2、每天需要生产100000份奶酪卖给超市
 * 3、冷库容量容量为1000份
 * 4、通过一辆货车发货，送货车每次送100份
 * 5、三条生产线，分别是牛奶供应生产线，发酵剂制作生产线，奶酪生产线
 */
public class CheeseFactory {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(100);

    private static BlockingDeque<Integer> storage =  new LinkedBlockingDeque(1000);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        for(int i=1;i<=1000;i++){
            //生产线
            //1、等待生产好两份牛奶
            //2、等待生产好一份发酵剂
            Future<String> milkFuture = executorService.submit(new MilkThread());
            Future<String> starterFuture = executorService.submit(new StarterCultureThread());
            System.out.println(milkFuture.get());
            System.out.println(starterFuture.get());
            //3、生产一份奶酪，入冷库
            Future<String> cheeseFuture = executorService.submit(new CheeseThread());
            System.out.println(cheeseFuture.get());
            storage.add(1);
            if(i%100 == 0){
                executorService.submit(new CarThread(storage,countDownLatch));
            }
            //运输
            //如果冷库每满1000份，发一车货
        }
    }

}

class CarThread implements Runnable{

    private BlockingDeque<Integer> storage;
    private CountDownLatch countDownLatch;
    public CarThread(BlockingDeque<Integer> storage,CountDownLatch countDownLatch){
        this.storage = storage;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
//            countDownLatch.await();
            System.out.println("-------------------生产好10个奶酪了，装车------------------");
            for(int i=0;i<100;i++){
                storage.take();
                System.out.println("搬走一份奶酪");
            }
            System.out.println("-------------------搬好10个奶酪了，发车------------------");
//            countDownLatch = new CountDownLatch(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MilkThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(30);
        return "生产完成2分牛奶";
    }
}

class StarterCultureThread implements Callable<String>{
    @Override
    public String call() throws Exception {
        Thread.sleep(15);
        return "生产完成1分发酵剂";
    }
}

class CheeseThread implements Callable<String>{
    @Override
    public String call() throws Exception {
        Thread.sleep(25);
        return "生产完成1分奶酪";
    }
}
