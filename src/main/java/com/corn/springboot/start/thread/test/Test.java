package com.corn.springboot.start.thread.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class Test {

    private static AtomicReference cas;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();
        map.put("a","a");
        Collections.synchronizedMap(map);

        loop:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("i=" + i + ",j=" + j);
                if (j == 5) {
                    break loop;
                }
            }
        }
    }

    public static void main2(String[] args) {

        Map<String, Object> map = new HashMap<>();


        //定义变量，保存两个字母
        char xiaoxie = 'a';
        char daxie = 'A';
        //定义循环，次数26次
        for (int i = 0; i < 26; i++) {
            //输出保存字母的变量
            System.out.println(xiaoxie + " " + daxie);
            xiaoxie++;
            daxie++;
        }

    }
}

abstract class TestAClass{
    void say(){
        System.out.println(1);
    }

    abstract void eat();
}

interface TestInterface{
//    void say(){
//        System.out.println(1);
//    }
}