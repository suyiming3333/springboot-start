package com.corn.springboot.start.spring.beanlifecircle.entity;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: asd
 * @Package com.corn.springboot.start.spring.beanlifecircle.entity
 * @Description: TODO
 * @date 2020/11/6 17:34
 */
public class Car {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }
}
