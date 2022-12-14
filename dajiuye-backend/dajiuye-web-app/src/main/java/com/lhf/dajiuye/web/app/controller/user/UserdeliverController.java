package com.lhf.dajiuye.web.app.controller.user;

import com.hu.health.common.constants.TopicConstants;
import com.hu.health.common.utils.JsonUtil;
import com.lhf.dajiuye.api.bean.user.Userdeliver;
import com.lhf.dajiuye.api.service.message.KafkaService;
import com.lhf.dajiuye.api.service.user.UserDeliverService;
import com.lhf.dajiuye.web.app.constants.RedisCacheName;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/user/deliver")
@CacheConfig(cacheNames = RedisCacheName.PREFIX +"/user/deliver")
public class UserdeliverController {

    @Autowired
    private ThreadPoolExecutor executor;

    @DubboReference(interfaceClass = KafkaService.class,version = "1.0.0",check = false)
    private KafkaService kafkaService;

    @DubboReference(interfaceClass = UserDeliverService.class,version = "1.0.0",check = false)
    UserDeliverService userDeliverService;
    /**
     * 用户发送简历
     * @param userdeliver
     * @return
     */
    @GetMapping(value = "/saveDeliver")
    public String saveDeliver(@ModelAttribute Userdeliver userdeliver){
        userDeliverService.saveDeliver(userdeliver);
        // 异步投递消息
        executor.submit(()->{
            kafkaService.sendMessage(TopicConstants.JOB_DELIVERY, userdeliver.getJobId());
        });
        return "保存信息成功";
    }
    /**
     * 更新简历投递状态
     * @param userdeliver
     * @return
     */
    @GetMapping(value = "/updateDeliver")
    public String updateDeliver(@ModelAttribute Userdeliver userdeliver){
        userDeliverService.updateDeliver(userdeliver);
        return "保存信息成功";
    }
}
