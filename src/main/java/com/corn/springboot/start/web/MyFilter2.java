package com.corn.springboot.start.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyFilter
 * @Package com.corn.springboot.start.web
 * @Description: TODO
 * @date 2020/10/22 16:42
 */
public class MyFilter2 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("before filter 2");
        filterChain.doFilter(servletRequest,servletResponse);
        servletResponse.getOutputStream().write("hello".getBytes());
        System.out.println("after filter 2");

    }
}
