package com.corn.springboot.start.web;

import cn.hutool.json.JSONUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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


        //原来的request的流只能取一次，mvc处理完请求后，无法再进行流的读取
//        filterChain.doFilter(new MyRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
        //这里是空字符串
//        String requestBodyString = new TestController().getRequestBodyString((HttpServletRequest) servletRequest);
        CacheRequestBodyRequestWrapper bodyRequestWrapper = new CacheRequestBodyRequestWrapper((HttpServletRequest) servletRequest);
        CacheResponseBodyResponseWrapper responseBodyWrapper = new CacheResponseBodyResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter( bodyRequestWrapper, responseBodyWrapper);
        String requestBodyString = new TestController().getRequestBodyString(bodyRequestWrapper);

        String responseString = responseBodyWrapper.getTextContent();
        System.out.println("请求参数"+requestBodyString);
        System.out.println("返回结果"+responseString);
        servletResponse.getOutputStream().write(responseBodyWrapper.getByteArrayOutputStream().toByteArray());

    }
}
