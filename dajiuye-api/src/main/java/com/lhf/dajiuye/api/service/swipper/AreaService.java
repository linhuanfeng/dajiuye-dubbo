package com.lhf.dajiuye.api.service.swipper;



import com.lhf.dajiuye.api.bean.City;
import com.lhf.dajiuye.api.bean.District;
import com.lhf.dajiuye.api.bean.Province;

import java.util.List;

public interface AreaService {
    /**
     * 获取省信息列表
     * @return
     */
    List<Province> getProvinceDataList();

    /**
     * 获取市列表
     * @return
     */
    List<City> getCityDataList();

    /**
     * 获取县信息列表
     * @return
     */
    List<District> getDistrictDataList();

    /**
     * 获取省市县信息列表
     * @return
     */
    List<Province> getFullPlaceDataList();
}
