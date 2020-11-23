package com.corn.springboot.start.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: LoginController
 * @Package com.corn.springboot.start.shiro
 * @Description: TODO
 * @date 2020/11/23 15:11
 */

@RestController
@Slf4j
public class LoginController {


    @PostMapping("/login")
    public String login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        String userName = httpServletRequest.getParameter("userName");
        String password = httpServletRequest.getParameter("password");
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                userName,
                password
        );

        subject.login(usernamePasswordToken);
        boolean hasRole = subject.hasRole("ADMIN");
        return "login success";
    }

    @GetMapping("/admin")
    @RequiresRoles("ADMIN")
    public String toAdmin(){
        return "admin role page";
    }
}
