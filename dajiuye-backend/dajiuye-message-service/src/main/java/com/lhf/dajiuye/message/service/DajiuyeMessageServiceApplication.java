package com.lhf.dajiuye.message.service;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
@MapperScan("com.lhf.dajiuye.message.service.mapper")
@DubboComponentScan("com.lhf.dajiuye.message.service.service.impl")
public class DajiuyeMessageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeMessageServiceApplication.class, args);
    }

}
