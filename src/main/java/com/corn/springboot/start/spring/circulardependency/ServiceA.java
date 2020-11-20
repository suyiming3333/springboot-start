package com.corn.springboot.start.spring.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ServiceA
 * @Package com.corn.springboot.start.spring.circulardependency
 * @Description: TODO
 * @date 2020/11/20 16:31
 */

@Component
public class ServiceA {

    private ServiceB serviceB;

    public ServiceA() {
        System.out.println("init service a");
    }

    @Autowired
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }


}
