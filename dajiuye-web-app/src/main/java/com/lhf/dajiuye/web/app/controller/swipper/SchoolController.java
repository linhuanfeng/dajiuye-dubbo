package com.lhf.dajiuye.web.app.controller.swipper;

import com.lhf.dajiuye.api.bean.CommonResult;
import com.lhf.dajiuye.api.bean.Meta;
import com.lhf.dajiuye.api.bean.School;
import com.lhf.dajiuye.api.service.swipper.SchoolService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/swipper/school/")
public class SchoolController {

    @DubboReference(interfaceClass = SchoolService.class,version = "1.0.0",check = false)
    private SchoolService schoolService;

    /**
     * 获取校招图片
     * @param schId
     * @return
     * @throws IOException
     */
    @RequestMapping("/schooldata")
    public Object schoolList(@RequestParam(value = "schoolId",required = false,defaultValue = "0") String schId) throws IOException {
        List<School> schoolDataList = schoolService.getSchoolDataList(Integer.parseInt(schId));
        return new CommonResult<School>(schoolDataList,new Meta("获取成功",200));
    }
}
