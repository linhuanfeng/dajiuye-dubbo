package com.lhf.dajiuye.web.app.controller.job;

import com.lhf.dajiuye.api.bean.CommonResult;
import com.lhf.dajiuye.api.bean.job.LCategory;
import com.lhf.dajiuye.api.bean.Meta;
import com.lhf.dajiuye.api.service.job.CategoryService;
import com.lhf.dajiuye.web.app.constants.RedisCacheName;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/job/category")
@CacheConfig(cacheNames = RedisCacheName.PREFIX +"/job/category")
public class CategoryController {

    @DubboReference(interfaceClass = CategoryService.class,version = "1.0.0",check = false)
    private CategoryService categoryService;

    /**
     * 获取职位分类信息
     * @return
     * @throws IOException
     */
    @GetMapping("/jobcatdata")
//    @PreAuthorize("hasAuthority('user.list')")
    @Cacheable
    public Object jobcatList() {
        List<LCategory> lCategoryDataList = categoryService.getFullCategoryDataList();
        return new CommonResult<LCategory>(lCategoryDataList,new Meta("获取成功",200));
    }
}
