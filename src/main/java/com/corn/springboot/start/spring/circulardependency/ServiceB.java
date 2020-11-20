package com.corn.springboot.start.spring.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ServiceB
 * @Package com.corn.springboot.start.spring.circulardependency
 * @Description: TODO
 * @date 2020/11/20 16:31
 */

@Component
public class ServiceB {

    private ServiceA serviceA;

    public ServiceB() {
        System.out.println("service b");
    }

    @Autowired
    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }



}
