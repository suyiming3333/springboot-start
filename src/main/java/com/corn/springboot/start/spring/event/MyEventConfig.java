package com.corn.springboot.start.spring.event;

import com.corn.springboot.start.spring.event.custom.EventListener;
import com.corn.springboot.start.spring.event.custom.EventMulticaster;
import com.corn.springboot.start.spring.event.custom.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyEventConfig
 * @Package com.corn.springboot.start.spring.event
 * @Description: TODO
 * @date 2020/11/20 11:07
 */

@Configuration
@ComponentScan
public class MyEventConfig {

    //注入spring实现eventListener接口的component
//    @Autowired
//    private List<EventListener> eventListeners;

    /**注册一个事件发布器**/
    @Bean
    @Autowired(required = false)
    public EventMulticaster eventMulticaster(List<EventListener> eventListeners) {
        EventMulticaster eventPublisher = new CommonEventMulticaster();
        if (eventListeners != null) {
            eventListeners.stream().forEach(eventPublisher::addEventListener);
        }
        return eventPublisher;
    }


    /***
     * applicationEventMulticaster 注入setTaskExecutor 实现异步事件
     * @return
     */
//    @Bean
//    public ApplicationEventMulticaster applicationEventMulticaster() {
//        //创建一个事件广播器
//        SimpleApplicationEventMulticaster result = new SimpleApplicationEventMulticaster();
//        //给广播器提供一个线程池，通过这个线程池来调用事件监听器
//        Executor executor = this.applicationEventMulticasterThreadPool().getObject();
//        //设置异步执行器
//        result.setTaskExecutor(executor);//@1
//        return result;
//    }

    /**
     * 自定义线程池
     *
     * **/
//    @Bean
//    public ThreadPoolExecutorFactoryBean applicationEventMulticasterThreadPool() {
//        ThreadPoolExecutorFactoryBean result = new ThreadPoolExecutorFactoryBean();
//        result.setThreadNamePrefix("applicationEventMulticasterThreadPool-");
//        result.setCorePoolSize(5);
//        return result;
//    }
}
