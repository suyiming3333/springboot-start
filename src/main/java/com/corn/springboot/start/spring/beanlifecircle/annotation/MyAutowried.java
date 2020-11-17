package com.corn.springboot.start.spring.beanlifecircle.annotation;

import java.lang.annotation.*;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyAutowored
 * @Package com.corn.springboot.start.spring.beanlifecircle.annotation
 * @Description: TODO
 * @date 2020/11/13 12:53
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAutowried {
}