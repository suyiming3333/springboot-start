package com.corn.springboot.start.spring.beanlifecircle.component;

import com.corn.springboot.start.spring.beanlifecircle.entity.Car;
import com.corn.springboot.start.spring.beanlifecircle.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyBeanDefinitionRegistry
 * @Package com.corn.springboot.start.spring.beanlifecircle.component
 * @Description: 自定义bean注册器
 * @date 2020/11/12 9:55
 */

@Component
public class MyBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //根据类型创建builder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Car.class.getName());
        //设置属性
        beanDefinitionBuilder.addPropertyValue("name","audi");
        //创建bean定义
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        registry.registerBeanDefinition("car",beanDefinition);

        //根据类型创建userbuilder
        BeanDefinitionBuilder userBeanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(User.class.getName());
        //设置属性
        userBeanDefinitionBuilder.addPropertyValue("name","corn");

        //注入依赖bean
        userBeanDefinitionBuilder.addPropertyReference("car","car");
        //创建bean定义
        BeanDefinition userBeanDefinition = userBeanDefinitionBuilder.getBeanDefinition();

        registry.registerBeanDefinition("user",userBeanDefinition);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
