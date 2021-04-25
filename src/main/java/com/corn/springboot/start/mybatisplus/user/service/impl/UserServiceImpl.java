package com.corn.springboot.start.mybatisplus.user.service.impl;

import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.corn.springboot.start.mybatisplus.user.mapper.UserMapper;
import com.corn.springboot.start.mybatisplus.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suyiming3333
 * @since 2020-10-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getByPage(Integer pageNo) {
        PageHelper.startPage(pageNo,15);
        List<User> list = userMapper.getByPage();
        return list;
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectUser(id);
    }

    @Override
    public void delUserById(Long userId) {
        userMapper.deleteById(userId);
    }
}
