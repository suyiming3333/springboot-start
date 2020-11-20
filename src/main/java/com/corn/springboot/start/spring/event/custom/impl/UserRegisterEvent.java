package com.corn.springboot.start.spring.event.custom.impl;

import com.corn.springboot.start.spring.event.custom.AbstractEvent;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: UserRegisterEvent
 * @Package com.corn.springboot.start.spring.event
 * @Description: 用户注册事件
 * @date 2020/11/20 10:44
 */
public class UserRegisterEvent extends AbstractEvent {

    public UserRegisterEvent(Object object,String userName) {
        super(object);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;
}
