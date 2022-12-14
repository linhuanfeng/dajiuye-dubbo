package com.lhf.dajiuye.web.app.controller.job;

import com.lhf.dajiuye.api.bean.CommonResult;
import com.lhf.dajiuye.api.bean.job.Company;
import com.lhf.dajiuye.api.bean.Meta;
import com.lhf.dajiuye.api.service.job.CompanyService;
import com.lhf.dajiuye.web.app.constants.RedisCacheName;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/job/company")
@CacheConfig(cacheNames = RedisCacheName.PREFIX +"/job/company")
public class CompanyController {

    @DubboReference(interfaceClass = CompanyService.class,version = "1.0.0",check = false)
    private CompanyService companyService;

    /**
     * 获取公司信息列表
     * @return
     * @throws IOException
     */
    @GetMapping("/comdata")
    @Cacheable
    public Object comdata(@RequestParam(value = "comId",required = false,defaultValue = "") String comId) throws IOException {
        List<Company> comDataList = companyService.getComData(comId);
        return new CommonResult<Company>(comDataList,new Meta("获取成功",200));
    }
}
