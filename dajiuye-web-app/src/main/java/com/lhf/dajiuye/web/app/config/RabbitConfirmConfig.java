package com.lhf.dajiuye.web.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

//@Configuration
@Slf4j
@Deprecated
public class RabbitConfirmConfig {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void setConfirmCallBack(){
        // 到达broker的回调
        //  CorrelationData correlationData, boolean ack, @Nullable String cause
        rabbitTemplate.setConfirmCallback((correlationData,ack,cause)->{
            if(ack){
                log.error("成功setConfirmCallBack");
            }else {
                log.error("失败setConfirmCallBack");
            }
        });
        // 到达队列失败才返回
        // ReturnedMessage
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey)->{
            log.info("setReturnCallback:{}",message);
        });
    }
}
