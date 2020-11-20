package com.corn.springboot.start.spring.event.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.corn.springboot.start.spring.event.custom.impl.*;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: UserRegisterService
 * @Package com.corn.springboot.start.spring.event
 * @Description: TODO
 * @date 2020/11/20 11:09
 */

@Service
public class UserRegisterService {

    @Autowired
    private EventMulticaster eventMulticaster;

    public void registerUser(String userName){
        System.out.println("用户注册成功："+userName);
        eventMulticaster.multicastEvent(new UserRegisterEvent(this,userName));
    }
}
