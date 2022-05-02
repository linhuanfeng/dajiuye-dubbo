package com.lhf.dajiuye.web.app.controller.swipper;

import com.lhf.dajiuye.api.bean.CommonResult;
import com.lhf.dajiuye.api.bean.Meta;
import com.lhf.dajiuye.api.bean.Province;
import com.lhf.dajiuye.api.service.swipper.AreaService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/swipper/area")
public class AreaController {

    @DubboReference(interfaceClass = AreaService.class,version = "1.0.0",check = false)
    private AreaService areaService;

    /**
     * 获取地点信息
     * @return
     * @throws IOException
     */
    @RequestMapping("/placedata")
    public Object placeList() {
        List<Province> provinceDataList = areaService.getFullPlaceDataList();
        System.out.println("###############\n"+provinceDataList);
        return new CommonResult<Province>(provinceDataList,new Meta("获取成功",200));
    }
}
