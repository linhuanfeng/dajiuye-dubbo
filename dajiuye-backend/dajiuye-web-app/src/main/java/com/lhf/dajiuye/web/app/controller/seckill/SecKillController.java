package com.lhf.dajiuye.web.app.controller.seckill;

import com.lhf.dajiuye.api.bean.CommonResult;
import com.lhf.dajiuye.api.bean.Meta;
import com.lhf.dajiuye.api.bean.seckill.SecLessonEntity;
import com.lhf.dajiuye.api.service.seckill.SecKillService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seckill/seckill")
@Deprecated
public class SecKillController {

    @DubboReference(interfaceClass = SecKillService.class,version = "1.0.0",check = false)
    private SecKillService secKillService;

    @Autowired
    RedisTemplate redisTemplate;

    // 秒杀下单
    @GetMapping("/current")
    public Object item(){
        List<SecLessonEntity> secLessonEntities = secKillService.curSec();
        return new CommonResult<SecLessonEntity>(secLessonEntities,new Meta("秒杀成功",200));
    }

    // 秒杀下单
    @GetMapping("/item")
    public String item(@RequestParam("userId")Long userId,
                       @RequestParam("skuId")Long skuId,
                       @RequestParam("secToken")String secToken){
        secKillService.secItem(userId, skuId, secToken);
        return "秒杀成功";
    }
}
