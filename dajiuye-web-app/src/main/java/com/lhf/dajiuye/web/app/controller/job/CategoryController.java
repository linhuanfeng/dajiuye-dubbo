package com.lhf.dajiuye.web.app.controller.job;

import com.lhf.dajiuye.api.bean.CommonResult;
import com.lhf.dajiuye.api.bean.LCategory;
import com.lhf.dajiuye.api.bean.Meta;
import com.lhf.dajiuye.api.service.job.CategoryService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/job/category")
public class CategoryController {

    @DubboReference(interfaceClass = CategoryService.class,version = "1.0.0")
    private CategoryService categoryService;

    /**
     * 获取职位分类信息
     * @return
     * @throws IOException
     */
    @RequestMapping("/jobcatdata")
//    @PreAuthorize("hasAuthority('user.list')")
    public Object jobcatList() {
        List<LCategory> lCategoryDataList = categoryService.getFullCategoryDataList();
        return new CommonResult<LCategory>(lCategoryDataList,new Meta("获取成功",200));
    }
}
