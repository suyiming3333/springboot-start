package com.corn.springboot.start.spring.value;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyUser
 * @Package com.corn.springboot.start.spring.value
 * @Description: TODO
 * @date 2020/11/19 15:51
 */

@Component
@MyScope
public class MyUser {

    private String username;

    public MyUser() {
        System.out.println("---------创建User对象" + this); //@2
        this.username = UUID.randomUUID().toString(); //@3
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
