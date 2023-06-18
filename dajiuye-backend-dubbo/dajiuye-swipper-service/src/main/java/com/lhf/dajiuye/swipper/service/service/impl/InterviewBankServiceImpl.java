package com.lhf.dajiuye.swipper.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.hu.health.common.utils.PageUtils;
import com.lhf.dajiuye.api.common.utils.Query;
import com.lhf.dajiuye.api.bean.swipper.InterviewBank;
import com.lhf.dajiuye.api.service.swipper.InterviewBankService;
import com.lhf.dajiuye.api.util.PageUtils;
import com.lhf.dajiuye.swipper.service.mapper.InterviewBankMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Map;

@Slf4j
@DubboService(interfaceClass = InterviewBankService.class,version = "1.0.0",retries = 0,timeout =10000)
public class InterviewBankServiceImpl extends ServiceImpl<InterviewBankMapper, InterviewBank> implements InterviewBankService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InterviewBank> page = this.page(
                new Query<InterviewBank>().getPage(params),
                new QueryWrapper<InterviewBank>()
        );

        return new PageUtils(page);
    }
}
