package com.corn.springboot.start.spring.beanlifecircle.component;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MySmartInitializingSingleton
 * @Package com.corn.springboot.start.spring.beanlifecircle.component
 * @Description: TODO
 * @date 2020/11/17 12:53
 */

/**
 * 所有单例bean加载完后会调用
 */
//@Component
public class MySmartInitializingSingleton implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("所有单例bean都加载完了");
    }
}
