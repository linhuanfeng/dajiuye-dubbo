package com.lhf.dajiuye.web.app.controller.swipper;

import com.lhf.dajiuye.api.bean.CatItems;
import com.lhf.dajiuye.api.bean.CommonResult;
import com.lhf.dajiuye.api.bean.Meta;
import com.lhf.dajiuye.api.bean.SwiperData;
import com.lhf.dajiuye.api.service.swipper.SwipperDataService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/swipper/swipper")
public class SwipperController {

    @DubboReference(interfaceClass = SwipperDataService.class,version = "1.0.0",check = false)
    private SwipperDataService swipperDataService;

    /**
     * 获取首页轮播图图片
     * @return
     * @throws IOException
     */
    @RequestMapping("/swiperdata")
    @PreAuthorize("hasAuthority('swipper:select')")
    public Object swiperdata() throws IOException {
        List<SwiperData> swiperDataList = swipperDataService.getswiperDataList();
        // System.out.println(swiperDataList);
        return new CommonResult<SwiperData>(swiperDataList,new Meta("获取成功",200));
    }

    /**
     * 获取分类导航图，比如校招、实习、分类、导航
     * @return
     * @throws IOException
     */
    @RequestMapping("/catitems")
    @PreAuthorize("hasAuthority('catitems:select')")
    public Object catitems()  {
        List<CatItems> catItemsList = swipperDataService.getcatItemsList();
        System.out.println(catItemsList);
        return new CommonResult<CatItems>(catItemsList,new Meta("获取成功",200));
    }
}
