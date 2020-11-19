package com.corn.springboot.start.spring.value;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ValueTest
 * @Package com.corn.springboot.start.spring.value
 * @Description: TODO
 * @date 2020/11/19 13:01
 */
public class ValueTest {

    @Test
    public void testValue(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyConfiguration.class);
        context.refresh();

        MyConfiguration dbConfig = context.getBean(MyConfiguration.class);
        System.out.println(dbConfig);
    }


    /***
     * @Values 加载其他数据来源的参数
     */
    @Test
    public void testValueFromCustomSource(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        /**自定义属性**/
        Map<String,Object> meMap = new HashMap<>();
        meMap.put("name","ccorn");
        meMap.put("age","19");
        MapPropertySource mailPropertySource = new MapPropertySource("me", meMap);
        /**添加到properties并添加到第一位**/
        context.getEnvironment().getPropertySources().addFirst(mailPropertySource);
        context.register(MyConfiguration.class);
        context.refresh();
        MyConfiguration dbConfig = context.getBean(MyConfiguration.class);
        System.out.println(dbConfig);
    }

    @Test
    public void testMyScope(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //将自定义作用域注册到spring容器中
        context.getBeanFactory().registerScope(MyBeanScope.SCOPE_MY, new MyBeanScope());//@1
        context.register(MyConfiguration.class);
        context.refresh();

        System.out.println("从容器中获取User对象");
        MyUser user = context.getBean(MyUser.class); //@2 获取得到的是cglib代理对象
        System.out.println("user对象的class为：" + user.getClass()); //@3

        System.out.println("多次调用user的getUsername感受一下效果\n");
        /**每次调用都会通过代理对象生成一个新对象**/
        for (int i = 1; i <= 3; i++) {
            System.out.println(String.format("********\n第%d次开始调用getUsername", i));
            System.out.println(user.getUsername());
            System.out.println(String.format("第%d次调用getUsername结束\n********\n", i));
        }
    }


    /**
     * 模拟springboot 2.0 RefreshScope 实现。动态刷新@VALUE注解
     */
    @Test
    public void testRefreshScope(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getBeanFactory().registerScope(MyBeanRefreshScope.SCOPE_MY, MyBeanRefreshScope.getInstance());

        /**自定义属性**/
        Map<String,Object> meMap = new HashMap<>();
        meMap.put("name","ccorn");
        meMap.put("age","19");
        MapPropertySource mailPropertySource = new MapPropertySource("me", meMap);
        /**添加到properties并添加到第一位**/
        context.getEnvironment().getPropertySources().addFirst(mailPropertySource);
        context.register(MyConfiguration.class);
        context.refresh();
        TestBean testBean = context.getBean(TestBean.class);
        testBean.soutBean();

        /**清空缓存**/
        MyBeanRefreshScope.getInstance().clean();
        meMap.put("name","suyiming");
        meMap.put("age","30");
        MapPropertySource mailPropertySource2 = new MapPropertySource("me", meMap);
        context.getEnvironment().getPropertySources().addFirst(mailPropertySource2);
        TestBean testBean2 = context.getBean(TestBean.class);
        testBean2.soutBean();

    }
}
