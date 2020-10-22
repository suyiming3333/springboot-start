package com.corn.springboot.start.web;

import cn.hutool.json.JSONUtil;

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
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = ((HttpServletRequest) servletRequest).getHeader("token");

        filterChain.doFilter(new MyRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        System.out.println(1);

    }
}
