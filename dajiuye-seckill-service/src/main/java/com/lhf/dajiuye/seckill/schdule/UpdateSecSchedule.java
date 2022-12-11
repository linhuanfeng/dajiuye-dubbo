package com.lhf.dajiuye.seckill.schdule;

import com.lhf.dajiuye.api.service.seckill.SecKillService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateSecSchedule {

    @Autowired
    private SecKillService secKillService;

    /**
     * second, minute, hour, day of month, month, and day of week
     */
    @Scheduled(cron = "1/30 * * * * ?")
    public void updateSec(){
        log.error("开始上架商品");
        secKillService.updateLatest3DayLesson();
    }
}
