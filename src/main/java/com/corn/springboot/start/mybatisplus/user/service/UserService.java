package com.corn.springboot.start.mybatisplus.user.service;

import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author suyiming3333
 * @since 2020-10-11
 */
public interface UserService extends IService<User> {

    List<User> getByPage(Integer pageNo);

    User getUserByName(String userName);

    User getUserById(Long id);

    void delUserById(Long userId);

}
