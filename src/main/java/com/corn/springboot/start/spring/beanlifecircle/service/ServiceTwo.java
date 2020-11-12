package com.corn.springboot.start.spring.beanlifecircle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ServiceTwo
 * @Package com.corn.springboot.start.spring.beanlifecircle.service
 * @Description: TODO
 * @date 2020/11/12 10:38
 */

public class ServiceTwo {

    @Autowired
    private ServiceOne serviceOne;

    @Override
    public String toString() {
        return "ServiceTwo{" +
                "serviceOne=" + serviceOne +
                '}';
    }
}
