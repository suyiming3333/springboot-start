package com.corn.springboot.start.web;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: MyRequestWrapper
 * @Package com.corn.springboot.start.web
 * @Description: TODO
 * @date 2020/10/22 17:24
 */
public class MyRequestWrapper extends HttpServletRequestWrapper {


    public MyRequestWrapper(HttpServletRequest request) {
        //So that other request method behave just like before
        super(request);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Enumeration<String> paramEnumeration = super.getParameterNames();
        ArrayList<String> paramList = Collections.list(paramEnumeration);
        Enumeration<String> headEnumeration = super.getParameterNames();
        ArrayList<String> headList = Collections.list(headEnumeration);
        //当有token字段时动态的添加uid字段
        if (headList.contains("token")) {
            paramList.add("uid");
            return Collections.enumeration(paramList);
        } else {
            return super.getParameterNames();
        }
    }

    @Override
    public String[] getParameterValues(String name) {
        if ("uid".equals(name)) {
            return new String[]{"1", "2", "3"};
        }
        return super.getParameterValues(name);
    }





}
