package com.lhf.dajiuye.swipper.service.service.impl;

import com.lhf.dajiuye.api.bean.School;
import com.lhf.dajiuye.api.service.swipper.SchoolService;
import com.lhf.dajiuye.swipper.service.mapper.SchoolDataMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(interfaceClass = SchoolService.class,version = "1.0.0")
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolDataMapper schoolDataMapper;

    /**
     * 获取校招列表数据
     * @param schoolId
     * @return
     */
    @Override
    public List<School> getSchoolDataList(int schoolId) {
        List<School> schoolDataList = schoolDataMapper.getSchoolDataList(schoolId);
        return schoolDataList;
    }
}
