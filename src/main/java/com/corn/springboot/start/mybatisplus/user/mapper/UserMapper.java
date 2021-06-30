package com.corn.springboot.start.mybatisplus.user.mapper;

import com.corn.springboot.start.mybatisplus.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author suyiming3333
 * @since 2020-10-11
 */
public interface UserMapper extends BaseMapper<User> {

    User selectUser(@Param("id") String id);

    User selectUser(@Param("id") Long id);


    User getUserByName(@Param("userName") String userName);

    List<User> getByPage();

}
