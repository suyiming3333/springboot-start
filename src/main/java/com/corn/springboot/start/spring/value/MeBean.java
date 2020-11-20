package com.corn.springboot.start.spring.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MeBean
 * @Package com.corn.springboot.start.spring.value
 * @Description: TODO
 * @date 2020/11/19 17:49
 */


@Component
@MyRefreshScope
public class MeBean {

    public MeBean(){
    }

    @Value("${name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Value("${age}")
    private String age;
}
