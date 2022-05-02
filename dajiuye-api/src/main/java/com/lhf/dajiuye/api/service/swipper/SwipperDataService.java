package com.lhf.dajiuye.api.service.swipper;



import com.lhf.dajiuye.api.bean.CatItems;
import com.lhf.dajiuye.api.bean.SwiperData;

import java.util.List;

public interface SwipperDataService {
    List<CatItems> getcatItemsList() ;
    List<SwiperData> getswiperDataList() ;
}
