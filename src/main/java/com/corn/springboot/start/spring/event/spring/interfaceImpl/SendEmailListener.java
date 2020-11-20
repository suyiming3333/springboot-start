package com.corn.springboot.start.spring.event.spring.interfaceImpl;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: SendEmailListener
 * @Package com.corn.springboot.start.spring.event.spring.interfaceImpl
 * @Description: 监听器实现spring listener接口
 * @date 2020/11/20 12:52
 */

@Component
public class SendEmailListener implements ApplicationListener<UserRegisterSucessEvent> {
    @Override
    public void onApplicationEvent(UserRegisterSucessEvent userRegisterSucessEvent) {
        System.out.println("用户注册成功,发送emali===spring interface"+userRegisterSucessEvent.getUserName());
    }
}
