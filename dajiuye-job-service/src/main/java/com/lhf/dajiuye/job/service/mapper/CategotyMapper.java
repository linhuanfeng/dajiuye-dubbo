package com.lhf.dajiuye.job.service.mapper;

import com.lhf.dajiuye.api.bean.LCategory;
import com.lhf.dajiuye.api.bean.MCategory;
import com.lhf.dajiuye.api.bean.SCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategotyMapper {
    /**
     * 获取大分类信息
     * @return
     */
    List<LCategory> getLargeCatDataList();

    /**
     * 获取中分类信息
     * @return
     */
    List<MCategory> getMiddleCatDataList();

    /**
     * 获取小分类信息
     * @return
     */
    List<SCategory> getSmallCatDataList();
}
