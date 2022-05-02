package com.lhf.dajiuye.acl.service.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhf.dajiuye.acl.service.mapper.UserRoleMapper;
import com.lhf.dajiuye.api.bean.acl.UserRole;
import com.lhf.dajiuye.api.service.acl.UserRoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
@DubboService(interfaceClass = UserRoleService.class,version = "1.0.0")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
