package com.lhf.dajiuye.api.service.job;



import com.lhf.dajiuye.api.bean.LCategory;
import com.lhf.dajiuye.api.bean.MCategory;
import com.lhf.dajiuye.api.bean.SCategory;

import java.util.List;

public interface CategoryService {
    /**
     * 获取大分类信息列表
     * @return
     */
    List<LCategory> getLargeDataList();

    /**
     * 获取中分类列表
     * @return
     */
    List<MCategory> getMiddleDataList();

    /**
     * 获取小分类信息列表
     * @return
     */
    List<SCategory> getSmallDataList();

    /**
     * 获取大中小信息列表
     * @return
     */
    List<LCategory> getFullCategoryDataList();
}
