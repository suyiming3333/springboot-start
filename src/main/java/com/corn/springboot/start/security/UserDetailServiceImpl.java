package com.corn.springboot.start.security;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.corn.springboot.start.system.user.entity.SysUser;
import com.corn.springboot.start.system.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(StrUtil.isNotBlank(s)){
            QueryWrapper<SysUser> userWrapper = new QueryWrapper<>();
            userWrapper.eq("user_name",s);
            SysUser user = sysUserServiceImpl.getOne(userWrapper);

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+"ADMIN"));
            return new User(user.getUserName(),"{noop}"+user.getPassword(),authorities);
        }

        return null;
    }
}
