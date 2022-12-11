package com.lhf.dajiuye.api.service.swipper;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hu.health.common.utils.PageUtils;
import com.lhf.dajiuye.api.bean.swipper.CommunityBank;
import com.lhf.dajiuye.api.bean.swipper.QuestionBank;

import java.util.Map;

public interface QuestionBankService extends IService<QuestionBank> {
    PageUtils queryPage(Map<String, Object> params);
}
