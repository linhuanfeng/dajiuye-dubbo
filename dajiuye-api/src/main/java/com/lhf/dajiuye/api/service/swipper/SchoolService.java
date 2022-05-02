package com.lhf.dajiuye.api.service.swipper;

import com.lhf.dajiuye.api.bean.School;

import java.util.List;

public interface SchoolService {

    /**
     * 获取校招列表数据
     * @param schId
     * @return
     */
    List<School> getSchoolDataList(int schId);
}
