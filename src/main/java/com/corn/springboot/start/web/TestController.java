package com.corn.springboot.start.web;

import com.corn.springboot.start.mybatisplus.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: TestController
 * @Package com.corn.springboot.start.web
 * @Description: TODO
 * @date 2020/10/22 16:00
 */

@RestController
@RequestMapping("")
public class TestController {


    @GetMapping(value = "/test")
    public String test(HttpServletRequest request,User user){
        System.out.println("user:"+user.getId());
        return "ok";
    }

    @GetMapping(value = "/test2")
    public String test2(HttpServletRequest request){
        String token = request.getHeader("token");
        System.out.println("user:"+token);
        return "ok";
    }
}
