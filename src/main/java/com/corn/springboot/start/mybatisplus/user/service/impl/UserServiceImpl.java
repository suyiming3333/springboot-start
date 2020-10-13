package com.corn.springboot.start.mybatisplus.user.service.impl;

import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.corn.springboot.start.mybatisplus.user.mapper.UserMapper;
import com.corn.springboot.start.mybatisplus.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
