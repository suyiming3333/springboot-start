package com.corn.springboot.start.mybatisplus.user.controller;


import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.corn.springboot.start.mybatisplus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author suyiming3333
 * @since 2020-10-11
 */
@RestController
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/list")
    public List<User> list(){
        List<User> users = userService.list();
        redisTemplate.opsForValue().set("list",users);
        return users;
    }

    @RequestMapping("/listFromRedis")
    public List<User> listFromRedis(){
        List<User> list = (List<User>) redisTemplate.opsForValue().get("list");
        return list;
    }

    @RequestMapping("/initDataToRedis")
    public void initDataToRedis(){
        List<User> users = userService.list();
        users.stream().forEach(c->{
            redisTemplate.opsForZSet().add("zlist",c.getName(),c.getAge());
        });
    }

    @RequestMapping("/rank")
    public String rank(){
        Set<String> set = redisTemplate.opsForZSet().rangeWithScores("zlist", 1L, 20L);
        return null;
    }
}

