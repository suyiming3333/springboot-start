package com.corn.springboot.start.sharding;


import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.corn.springboot.start.mybatisplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/sharding")
public class ShardingJdbcController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/test")
    public List<User> test(HttpServletRequest request){
        System.out.println("111");
        List<User> list = userService.getByPage(1);
        return list;
    }

    @GetMapping(value = "/getByAge/{age}")
    public List<User> getByAge(@PathVariable("age") Integer age){
        System.out.println("getByAge");
        List<User> list = userService.getListByAge(age);
        return list;
    }

    @GetMapping(value = "/getById/{id}")
    public List<User> getById(@PathVariable("id") String id){
        System.out.println("getById");
        List<User> list = userService.getById(id);
        return list;
    }

    @GetMapping(value = "/addUser")
    public Boolean addUser(@RequestBody User user){
        boolean save = userService.save(user);
        return save;
    }
}
