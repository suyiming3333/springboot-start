package com.corn.springboot.start.spring.container.modeltwo;

import com.corn.springboot.start.spring.container.modelone.MyService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyService3
 * @Package com.corn.springboot.start.spring.container.modeltwo
 * @Description: TODO
 * @date 2020/11/18 15:48
 */

//@Component
public class MyService3 {

    @Autowired
    private MyService myService;

    /**
     * 不通的扫描路径，不通容器无法加载
     */
//    @Autowired
//    private com.corn.springboot.start.spring.container.modelone.MyService myService;


    @Autowired
    private MyService2 myService2;

    public void methodOne(){
        myService.method();
    }

    public void methodTwo(){
        myService.method();
    }

    public void method3(){
        myService2.method();
    }
}
