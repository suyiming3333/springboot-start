package com.corn.springboot.start.spring.value;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyBeanScope
 * @Package com.corn.springboot.start.spring.value
 * @Description: 自定义scope作用域
 * @date 2020/11/19 15:47
 */
public class MyBeanScope implements Scope {

    public static final String SCOPE_MY = "my";

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        System.out.println("MyBeanScope >>>>>>>>> get:" + name); //@2
        return objectFactory.getObject();
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
