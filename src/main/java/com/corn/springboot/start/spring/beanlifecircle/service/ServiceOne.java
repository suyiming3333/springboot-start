package com.corn.springboot.start.spring.beanlifecircle.service;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ServiceOne
 * @Package com.corn.springboot.start.spring.beanlifecircle.service
 * @Description: TODO
 * @date 2020/11/12 10:37
 */

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Primary
@Lazy
public class ServiceOne {
}
