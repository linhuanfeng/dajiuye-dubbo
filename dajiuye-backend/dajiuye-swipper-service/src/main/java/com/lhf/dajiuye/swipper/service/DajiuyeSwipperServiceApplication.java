package com.lhf.dajiuye.swipper.service;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@DubboComponentScan("com.lhf.dajiuye.swipper.service.service.impl")
@MapperScan("com.lhf.dajiuye.swipper.service.mapper")
@EnableCaching
public class DajiuyeSwipperServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeSwipperServiceApplication.class, args);
    }

}
