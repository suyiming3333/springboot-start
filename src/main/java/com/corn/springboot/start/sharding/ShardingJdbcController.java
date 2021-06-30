package com.corn.springboot.start.sharding;


import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.corn.springboot.start.mybatisplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<User> list = userService.list();
        return list;
    }

    @GetMapping(value = "/addUser")
    public Boolean addUser(@RequestBody User user){
        boolean save = userService.save(user);
        return save;
    }
}
