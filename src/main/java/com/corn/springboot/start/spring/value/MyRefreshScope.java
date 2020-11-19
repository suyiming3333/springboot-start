package com.corn.springboot.start.spring.value;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.lang.annotation.*;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyScope
 * @Package com.corn.springboot.start.spring.value
 * @Description: TODO
 * @date 2020/11/19 15:50
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Scope(MyBeanRefreshScope.SCOPE_MY) //@1
public @interface MyRefreshScope {
    /**
     * @see Scope#proxyMode()
     *
     * 通过代理 去创建 Myscope声明的 对象
     */
    ScopedProxyMode proxyMode() default ScopedProxyMode.TARGET_CLASS;//@2
}
