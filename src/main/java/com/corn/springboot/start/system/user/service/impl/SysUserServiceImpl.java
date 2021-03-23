package com.corn.springboot.start.system.user.service.impl;

import com.corn.springboot.start.system.user.entity.SysUser;
import com.corn.springboot.start.system.user.mapper.SysUserMapper;
import com.corn.springboot.start.system.user.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author suyiming3333
 * @since 2021-01-12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
