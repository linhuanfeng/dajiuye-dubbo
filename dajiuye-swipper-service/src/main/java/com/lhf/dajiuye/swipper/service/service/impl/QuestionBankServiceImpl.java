package com.lhf.dajiuye.swipper.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hu.health.common.utils.PageUtils;
import com.hu.health.common.utils.Query;
import com.lhf.dajiuye.api.bean.swipper.InterviewBank;
import com.lhf.dajiuye.api.bean.swipper.QuestionBank;
import com.lhf.dajiuye.api.service.swipper.InterviewBankService;
import com.lhf.dajiuye.api.service.swipper.QuestionBankService;
import com.lhf.dajiuye.swipper.service.mapper.InterviewBankMapper;
import com.lhf.dajiuye.swipper.service.mapper.QuestionBankMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Map;

@Slf4j
@DubboService(interfaceClass = QuestionBankService.class,version = "1.0.0",retries = 0,timeout =10000)
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank> implements QuestionBankService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<QuestionBank> page = this.page(
                new Query<QuestionBank>().getPage(params),
                new QueryWrapper<QuestionBank>()
        );

        return new PageUtils(page);
    }
}
