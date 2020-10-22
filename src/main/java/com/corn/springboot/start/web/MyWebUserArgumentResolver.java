package com.corn.springboot.start.web;

import com.corn.springboot.start.mybatisplus.user.entity.User;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyWebConfig
 * @Package com.corn.springboot.start.web
 * @Description:自定义参数处理器
 * @date 2020/10/22 15:38
 */
public class MyWebUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return User.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest nativeRequest = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        String token = nativeRequest.getHeader("token");
        User user = new User();
        user.setId(Long.valueOf(token));
        return user;
    }
}
