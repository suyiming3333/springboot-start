package com.corn.springboot.start.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;

import java.util.List;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyWebConfig
 * @Package com.corn.springboot.start.web
 * @Description: TODO
 * @date 2020/10/22 15:40
 */

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    /***
     * 添加自定义参数处理器
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new MyWebUserArgumentResolver());
    }


    /**
     * 过滤顺序为小到大
     * @return
     */
    @Bean
    public FilterRegistrationBean myFilterBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new MyFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(Integer.MAX_VALUE);
        return bean;
    }

    @Bean
    public FilterRegistrationBean myFilterBeanb(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new MyFilter2());
        bean.addUrlPatterns("/*");
        bean.setOrder(Integer.MAX_VALUE-1);
        return bean;
    }
}
