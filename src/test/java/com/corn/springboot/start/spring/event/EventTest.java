package com.corn.springboot.start.spring.event;

import com.corn.springboot.start.spring.event.custom.UserRegisterService;
import com.corn.springboot.start.spring.event.spring.interfaceImpl.UserRegisterServiceImpl;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: EventTest
 * @Package com.corn.springboot.start.spring.event
 * @Description: TODO
 * @date 2020/11/20 11:12
 */
public class EventTest {

    @Test
    public void testRegisterEvent(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyEventConfig.class);
        context.refresh();
        UserRegisterService userRegisterService = context.getBean(UserRegisterService.class);
        userRegisterService.registerUser("13265905397");
    }

    @Test
    public void testRegisterSringEvent(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyEventConfig.class);
        context.refresh();
        UserRegisterServiceImpl userRegisterService = context.getBean(UserRegisterServiceImpl.class);
        userRegisterService.registerUser("13265905397");
    }

}
