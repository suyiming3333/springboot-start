package com.corn.springboot.start.spring.beanlifecircle.entity;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: qw
 * @Package com.corn.springboot.start.spring.beanlifecircle.entity
 * @Description: TODO
 * @date 2020/11/6 17:34
 */
public class User {
    private String name;

    private Car car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", car=" + car +
                '}';
    }
}