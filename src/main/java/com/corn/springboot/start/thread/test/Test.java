package com.corn.springboot.start.thread.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Test {

    private static AtomicReference cas;

    public static void main(String[] args) {

        Map<String,Object> map = new HashMap<>();


        //定义变量，保存两个字母
        char xiaoxie = 'a';
        char daxie = 'A';
        //定义循环，次数26次
        for(int i = 0 ; i < 26 ; i++ ){
            //输出保存字母的变量
            System.out.println(xiaoxie+" "+daxie);
            xiaoxie++;
            daxie++;
        }

    }
}
