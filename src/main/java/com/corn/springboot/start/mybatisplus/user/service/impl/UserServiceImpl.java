package com.corn.springboot.start.mybatisplus.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.corn.springboot.start.mybatisplus.user.mapper.UserMapper;
import com.corn.springboot.start.mybatisplus.user.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getByPage(Integer pageNo) {
        PageHelper.startPage(pageNo,3);
        List<User> list = userMapper.getByPage();
        return list;
    }

    @Override
    public boolean save(User entity) {
        super.save(entity);
        if(true){
            throw new RuntimeException("tex exception");
        }
        return false;
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

    @Override
    public List<User> getListByAge(Integer age) {
        PageHelper.startPage(1,15);
        List<User> list =userMapper.getListByAge(age);
        return list;
    }

    @Override
    public List<User> getById(String id) {
        PageHelper.startPage(1,15);
        List<User> list =userMapper.getById(id);
        return list;    }
}
