package com.lhf.dajiuye.user.service.service.impl;

import com.lhf.dajiuye.api.bean.Userdeliver;
import com.lhf.dajiuye.api.service.user.UserDeliverService;
import com.lhf.dajiuye.user.service.mapper.UserDeliverMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(interfaceClass = UserDeliverService.class,version = "1.0.0")
public class UserDeliverServiceImpl implements UserDeliverService {

    @Resource
    private UserDeliverMapper mapper;

    @Override
    public void saveDeliver(Userdeliver userdeliver) {
        mapper.saveDeliver(userdeliver);
    }

    @Override
    public void updateDeliver(Userdeliver userdeliver) {
        mapper.updateDeliver(userdeliver);
    }
}
