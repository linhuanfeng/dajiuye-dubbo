package com.lhf.dajiuye.swipper.service.service.impl;

import com.lhf.dajiuye.api.bean.CatItems;
import com.lhf.dajiuye.api.bean.SwiperData;
import com.lhf.dajiuye.api.service.swipper.SwipperDataService;
import com.lhf.dajiuye.swipper.service.mapper.SwiperDataMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@DubboService(interfaceClass = SwipperDataService.class,version = "1.0.0")
public class SwipperDataServiceImpl implements SwipperDataService {

    @Autowired
    private SwiperDataMapper mapper;

    /**
     * 获取轮播图数据
     *
     * @return
     * @throws IOException
     */
    @Override
    public List<SwiperData> getswiperDataList() {
        List<SwiperData> swiperDataList = mapper.getSwiperDataList();
        return swiperDataList;
    }

    /**
     * 获取分类导航栏数据
     *
     * @return
     * @throws IOException
     */
    @Override
    public List<CatItems> getcatItemsList() {
        List<CatItems> catItemsList = mapper.getcatItemsDataList();
        return catItemsList;
    }
}
