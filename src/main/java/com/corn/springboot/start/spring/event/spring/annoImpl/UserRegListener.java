package com.corn.springboot.start.spring.event.spring.annoImpl;

import com.corn.springboot.start.spring.event.spring.interfaceImpl.UserRegisterSucessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: UserRegListener
 * @Package com.corn.springboot.start.spring.event.spring.annoImpl
 * @Description: TODO
 * @date 2020/11/20 13:02
 */

@Component
public class UserRegListener {


    @EventListener
    @Order(1)
    public void sendMail(UserRegisterSucessEvent event){
        System.out.println(String.format("给用户【%s】发送邮件通知!", event.getUserName()));

    }

    @EventListener
    @Order(2)
    public void sendCompon(UserRegisterSucessEvent event) {
        System.out.println(String.format("给用户【%s】发送优惠券!", event.getUserName()));
    }
}
