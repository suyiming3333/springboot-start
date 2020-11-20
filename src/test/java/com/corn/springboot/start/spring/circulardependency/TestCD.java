package com.corn.springboot.start.spring.circulardependency;

import com.corn.springboot.start.spring.value.MyConfiguration;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: TestCD
 * @Package com.corn.springboot.start.spring.circulardependency
 * @Description: TODO
 * @date 2020/11/20 16:35
 */
public class TestCD {

    @Test
    public void testC(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ConfigurationAB.class);
        context.refresh();

    }
}
