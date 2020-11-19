package com.corn.springboot.start.spring.value;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyConfiguration
 * @Package com.corn.springboot.start.spring.value
 * @Description: TODO
 * @date 2020/11/19 12:54
 */
@Configuration
@ComponentScan
public class MyConfiguration {

    @Value("${me.name}")
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

    @Value("${me.age}")
    private String age;
}
