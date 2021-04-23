package com.corn.springboot.start.mybatisplus.user.controller;


import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.corn.springboot.start.mybatisplus.user.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
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


    @RequestMapping("/list/{pageNo}")
    public List<User> list(@PathVariable("pageNo")Integer pageNo){



        List<User> users = userService.getByPage(pageNo);
        ListOperations listOperations = redisTemplate.opsForList();
        users.stream().forEach(c->{
            listOperations.leftPush("userId:"+c.getId(),c);
        });
        //redisTemplate.opsForValue().set("list",users);
        return users;
    }

    @RequestMapping("/listRedis/{pageNo}")
    public List<User> listRedis(@PathVariable("pageNo")Integer pageNo){
        ListOperations listOperations = redisTemplate.opsForList();
        List<User> list = listOperations.range("userIds:", 0L, 15L);
        return list;
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

    @RequestMapping("/putDataToList")
    public String putDataToList(){
        List<User> users = userService.list();
        ListOperations listOperations = redisTemplate.opsForList();
        users.stream().forEach(c->{
            listOperations.leftPush("userIds:",c);
        });

        return "ok";
    }
}

