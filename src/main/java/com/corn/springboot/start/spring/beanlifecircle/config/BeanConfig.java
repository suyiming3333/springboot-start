package com.corn.springboot.start.spring.beanlifecircle.config;

import com.corn.springboot.start.spring.beanlifecircle.entity.Car;
import org.springframework.context.annotation.Bean;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: BeanConfig
 * @Package com.corn.springboot.start.spring.beanlifecircle.config
 * @Description: TODO
 * @date 2020/11/12 11:26
 */
public class BeanConfig {

    @Bean()
    public Car car(){
        return new Car();
    }
}
