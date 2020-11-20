package com.corn.springboot.start.spring.event.custom.impl;

import com.corn.springboot.start.spring.event.custom.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: SendMailOnUserRegisterEvent
 * @Package com.corn.springboot.start.spring.event
 * @Description: 用户注册事件监听
 * @date 2020/11/20 11:04
 */

@Component
public class SendMailOnUserRegisterEventListener implements EventListener<UserRegisterEvent> {
    @Override
    public void onEvnent(UserRegisterEvent event) {
        System.out.println(event.getUserName()+"用户注册成功，已发送email");
    }
}
