package com.lhf.dajiuye.user.service;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lhf.dajiuye.user.service.mapper")
@DubboComponentScan("com.lhf.dajiuye.user.service.service.impl")
public class DajiuyeUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeUserServiceApplication.class, args);
    }

}
