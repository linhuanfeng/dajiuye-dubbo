package com.lhf.dajiuye.job.service.service.impl;

import com.lhf.dajiuye.api.bean.Company;
import com.lhf.dajiuye.api.service.job.CompanyService;
import com.lhf.dajiuye.job.service.mapper.CompanyMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService(interfaceClass = CompanyService.class, version = "1.0.0")
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper mapper;

    @Override
    public List<Company> getComData(String comId) {
        return mapper.getComDataList(comId);
    }
}
