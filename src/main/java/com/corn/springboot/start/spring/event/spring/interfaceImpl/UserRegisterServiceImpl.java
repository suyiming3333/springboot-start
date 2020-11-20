package com.corn.springboot.start.spring.event.spring.interfaceImpl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: UserRegisterServiceImpn
 * @Package com.corn.springboot.start.spring.event.spring.interfaceImpl
 * @Description: 实现spring ApplicationEventPublisherAware回调接口
 * @date 2020/11/20 12:54
 */

@Component
public class UserRegisterServiceImpl implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void registerUser(String userName){
        System.out.println("用户注册成功："+userName);
        this.applicationEventPublisher.publishEvent(new UserRegisterSucessEvent(this,userName));
    }
}
