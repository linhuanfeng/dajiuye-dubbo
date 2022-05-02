package com.lhf.dajiuye.swipper.service.mapper;

import com.lhf.dajiuye.api.bean.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SchoolDataMapper {

    /**
     * 获取校招实体信息
     * @param schId
     * @return
     */
    @Select("<script>        select * from school\n" +
            "        <where>\n" +
            "            <if test=\"schId!=0\">\n" +
            "                and schId=1\n" +
            "            </if>\n" +
            "        </where></script>")
    List<School> getSchoolDataList(int schId);
}
