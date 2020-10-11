package com.corn.springboot.start;

import com.corn.springboot.start.user.entity.User;
import com.corn.springboot.start.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        List<User> userList = userService.list();
        User user = new User();
        user.setName("xxssww");
        userService.save(user);
        System.out.println(1);
    }
}
