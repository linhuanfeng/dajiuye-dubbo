package com.lhf.dajiuye.web.app.log;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 监听容器事件,把日志产生事件发送到kafka集群
 * 再由logstash进行解析反序列化后发送es中，kibana进行可视化
 */
@Slf4j
@RequiredArgsConstructor // 写在类上可以代替@AutoWired注解，需要注意的是在注入时需要用final定义，或者使用@notnull注解
@Component
public class SysLogListener {

//	private final SysLogMapper sysLogMapper;

	@Autowired
	private KafkaProducer<String, String> kafkaProducer;

	@Async
	@Order
	@EventListener(SysLogEvent.class)
	public void saveSysLog(SysLogEvent event) {
		SysLog sysLog = (SysLog) event.getSource();
		System.out.println(sysLog);
		kafkaProducer.send(new ProducerRecord<>("job", JSON.toJSONString(sysLog)));
//		sysLogMapper.insert(sysLog);
	}
}
