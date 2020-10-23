package com.corn.springboot.start.web;

import com.corn.springboot.start.mybatisplus.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

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
    public String test(HttpServletRequest request,User user,String uid){
        System.out.println("user:"+user.getId());
        return "ok";
    }

    @GetMapping(value = "/test2")
    public String test2(HttpServletRequest request) throws IOException {
        String token = request.getHeader("token");
        String requestBodyString = getRequestBodyString(request);
        System.out.println("user:"+token);
        return "ok";
    }


    public String getRequestBodyString(HttpServletRequest request){
        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;

        try{
            bufferedReader =  request.getReader() ;
            char[] charBuffer = new char[128];
            int bytesRead;
            while((bytesRead = bufferedReader.read(charBuffer))!=-1){
                sb.append(charBuffer, 0, bytesRead);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
