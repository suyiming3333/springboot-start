package com.corn.springboot.start.spring.beanlifecircle.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: asd
 * @Package com.corn.springboot.start.spring.beanlifecircle.entity
 * @Description: TODO
 * @date 2020/11/16 13:34
 */

public class AwareBean implements BeanNameAware
                                , BeanClassLoaderAware
                                , BeanFactoryAware
                                , EnvironmentAware
                                , EmbeddedValueResolverAware
                                , ResourceLoaderAware
                                , ApplicationEventPublisherAware
                                , MessageSourceAware
                                , ApplicationContextAware {

    public AwareBean(){
        System.out.println("init constructor");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName：" + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory：" + beanFactory);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("setBeanClassLoader：" + classLoader);
    }

    @PostConstruct
    public void postConstruct1() { //@1
        System.out.println("postConstruct1()");
    }

    @PostConstruct
    public void postConstruct2() { //@2
        System.out.println("postConstruct2()");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext:" + applicationContext);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        System.out.println("setApplicationEventPublisher:" + applicationEventPublisher);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        System.out.println("setEmbeddedValueResolver:" + resolver);
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("setEnvironment:" + environment.getClass());
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        System.out.println("setMessageSource:" + messageSource);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println("setResourceLoader:" + resourceLoader);
    }
}
