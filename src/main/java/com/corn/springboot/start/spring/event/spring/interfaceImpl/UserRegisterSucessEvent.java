package com.corn.springboot.start.spring.event.spring.interfaceImpl;

import org.springframework.context.ApplicationEvent;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: UserRegisterEvent
 * @Package com.corn.springboot.start.spring.event
 * @Description: 实现sping 事件接口
 * @date 2020/11/20 10:44
 */
public class UserRegisterSucessEvent extends ApplicationEvent {

    public UserRegisterSucessEvent(Object object, String userName) {
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
