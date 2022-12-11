package com.lhf.dajiuye.web.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;


//@EnableRabbit
//@Configuration
@Slf4j
@Deprecated
public class RabbitMsgConvertConfig implements ApplicationContextAware {

//    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        // 消息到交换的回调
        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) -> {
            if(ack){
                log.info("ack");
            }else {
                log.error("失败：{}",new String(cause.getBytes()));
            }
        }));
        // 消息到队列的回调
        rabbitTemplate.setReturnCallback((message,replyCode, replyText, exchange,routingKey) ->
                log.error("setReturnCallback:{}",message));
    }
}
