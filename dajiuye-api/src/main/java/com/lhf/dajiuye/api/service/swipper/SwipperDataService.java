package com.lhf.dajiuye.api.service.swipper;



import com.lhf.dajiuye.api.bean.swipper.CatItems;
import com.lhf.dajiuye.api.bean.swipper.SwiperData;

import java.util.List;

public interface SwipperDataService {
    List<CatItems> getcatItemsList() ;
    List<SwiperData> getswiperDataList() ;
}
