package com.corn.springboot.start.spring.container.modelone;

import com.corn.springboot.start.spring.container.modeltwo.MyService4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ServiceOne
 * @Package com.corn.springboot.start.spring.container.modelone
 * @Description: TODO
 * @date 2020/11/18 15:43
 */

@Component
public class MyService2 {

    @Autowired
    private MyService myService;

    public void method(){
        myService.method();
        System.out.println("MyService2 method from module one");
    }
}
