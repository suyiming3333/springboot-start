package com.corn.springboot.start.spring.factorybean;

import com.corn.springboot.start.mybatisplus.user.service.UserService;
import com.corn.springboot.start.spring.factorybean.Programmer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.beans.factory.BeanFactory.FACTORY_BEAN_PREFIX;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void test(){
        Programmer programmera = (Programmer) applicationContext.getBean("aProgrammer");
        Programmer programmerb = (Programmer) applicationContext.getBean("bProgrammer");
        Object o = applicationContext.getBean(FACTORY_BEAN_PREFIX+"aProgrammer");
        Object o2 = applicationContext.getBean(FACTORY_BEAN_PREFIX+"bProgrammer");
        programmera.coding();
    }
}
