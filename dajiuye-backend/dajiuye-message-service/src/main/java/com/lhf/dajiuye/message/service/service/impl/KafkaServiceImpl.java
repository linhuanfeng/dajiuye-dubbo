package com.lhf.dajiuye.message.service.service.impl;

import com.hu.health.common.constants.TopicConstants;
import com.hu.health.common.utils.JsonUtil;
import com.lhf.dajiuye.api.service.job.JobDataService;
import com.lhf.dajiuye.api.service.message.KafkaService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@DubboService(interfaceClass = KafkaService.class, version = "1.0.0")
public class KafkaServiceImpl implements KafkaService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @DubboReference(interfaceClass = JobDataService.class, version = "1.0.0", check = false)
    private JobDataService jobDataService;

    @Override
    public void sendMessage(String message) {
        kafkaTemplate.send(TopicConstants.JOB_HOT, message);
    }

    @Override
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    // 监听职位浏览事件
    @KafkaListener(id = TopicConstants.JOB_HOT, topics = TopicConstants.JOB_HOT)
    public void jobAddScore(String message) {
        jobDataService.addJobScore(message);
    }

    // 监听职位浏览事件
    @KafkaListener(id = TopicConstants.JOB_BROWSE, topics = TopicConstants.JOB_BROWSE)
    public void jobBrowse(String message) {
        jobDataService.addJobScore(message);
    }

    // 监听职位投递事件
    @KafkaListener(id =  TopicConstants.JOB_DELIVERY, topics = TopicConstants.JOB_DELIVERY)
    public void jobDelivery(String message) {
        jobDataService.jobDelivery(message);
    }
}
