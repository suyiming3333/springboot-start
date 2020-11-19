package com.corn.springboot.start.spring.container;

import com.corn.springboot.start.spring.container.modelone.ModelOneConf;
import com.corn.springboot.start.spring.container.modelone.MyService2;
import com.corn.springboot.start.spring.container.modeltwo.ModelTwoConf;
import com.corn.springboot.start.spring.container.modeltwo.MyService3;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: ContainerTest
 * @Package com.corn.springboot.start.spring.container
 * @Description: TODO
 * @date 2020/11/18 16:01
 */
public class ContainerTest {

    @Test
    public void BeanConfitTest(){
        //定义容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册bean
        context.register(ModelOneConf.class, ModelTwoConf.class); //@1
        //启动容器
        context.refresh();
    }

    /**
     * 父容器和子容器是相互隔离的，他们内部可以存在名称相同的bean
     * 子容器可以访问父容器中的bean，而父容器不能访问子容器中的bean
     * 调用子容器的getBean方法获取bean的时候，会沿着当前容器开始向上面的容器进行查找，直到找到对应的bean为止
     * 子容器中可以通过任何注入方式注入父容器中的bean，而父容器中是无法注入子容器中的bean，原因是第2点
     */
    @Test
    public void ParentChildContainerTest(){
        //创建父容器
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        //向父容器中注册Module1Config配置类
        parentContext.register(ModelOneConf.class);
        //启动父容器
        parentContext.refresh();

        //创建子容器
        AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext();
        //向子容器中注册Module2Config配置类
        childContext.register(ModelTwoConf.class);
        //给子容器设置父容器
        childContext.setParent(parentContext);
        //启动子容器
        childContext.refresh();

        //从子容器中获取自己容器的Service3
        MyService3 service3 = childContext.getBean(MyService3.class);
        service3.methodOne();
        service3.methodTwo();
        service3.method3();

        //从子容器获取父容器的Service2
        MyService2 service2 = childContext.getBean(MyService2.class);
        service2.method();

        //打算在父容器中加载子容器的Service3，但是报错了
//        MyService3 service33 = parentContext.getBean(MyService3.class);
//        service33.methodOne();
//        service33.methodTwo();
//        service33.method3();
        /**getBeanNamesForType 只获取本容器的bean**/
        System.out.println(Arrays.asList(childContext.getBeanNamesForType(com.corn.springboot.start.spring.container.modelone.MyService.class)));//@2
        System.out.println(Arrays.asList(childContext.getBeanNamesForType(com.corn.springboot.start.spring.container.modeltwo.MyService.class)));//@2

        String[] beanNamesForTypeIncludingAncestors = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(parentContext, com.corn.springboot.start.spring.container.modelone.MyService.class);

        String[] beanNamesForTypeIncludingAncestors2 = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(childContext, com.corn.springboot.start.spring.container.modeltwo.MyService.class);

        System.out.println(Arrays.asList(beanNamesForTypeIncludingAncestors));
        System.out.println(Arrays.asList(beanNamesForTypeIncludingAncestors2));


    }
}
