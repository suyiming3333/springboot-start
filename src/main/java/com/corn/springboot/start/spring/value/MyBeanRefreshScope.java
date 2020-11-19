package com.corn.springboot.start.spring.value;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyBeanScope
 * @Package com.corn.springboot.start.spring.value
 * @Description: 自定义scope作用域
 * @date 2020/11/19 15:47
 */
public class MyBeanRefreshScope implements Scope {

    public static final String SCOPE_MY = "my_refresh_scope";

    public static final MyBeanRefreshScope INSTANCE = new MyBeanRefreshScope();

    private ConcurrentHashMap<String,Object> beanMap = new ConcurrentHashMap<>();

    /**单例禁止实例化**/
    private MyBeanRefreshScope(){

    }

    /**单例获取**/
    public static MyBeanRefreshScope getInstance() {
        return INSTANCE;
    }

    /**清除beanmap缓存的数据**/
    public void clean() {
        INSTANCE.beanMap.clear();
    }


    /****
     * 会通过get返回bean对象
     * @param name
     * @param objectFactory
     * @return
     */
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object o = beanMap.get(name);
        if(o != null){
            System.out.println("缓存里面有,直接返回");
            return o;
        }else{
            System.out.println("缓存里面没有,生成新的对象");
            Object newO = objectFactory.getObject();
            beanMap.put(name,newO);
            return newO;
        }

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
