package com.lhf.dajiuye.acl.service.service.impl;


import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhf.dajiuye.acl.service.mapper.RolePermissionMapper;
import com.lhf.dajiuye.api.bean.acl.RolePermission;
import com.lhf.dajiuye.api.service.acl.IndexService;
import com.lhf.dajiuye.api.service.acl.RolePermissionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
@DubboService(interfaceClass = RolePermissionService.class,version = "1.0.0")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
