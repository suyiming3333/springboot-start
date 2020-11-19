package com.corn.springboot.start.spring.value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: TestBean
 * @Package com.corn.springboot.start.spring.value
 * @Description: TODO
 * @date 2020/11/19 18:19
 */

@Component
public class TestBean {

    @Autowired
    private MeBean meBean;


    public void soutBean(){
        System.out.println(meBean.getName()+ meBean.getAge());
    }
}
