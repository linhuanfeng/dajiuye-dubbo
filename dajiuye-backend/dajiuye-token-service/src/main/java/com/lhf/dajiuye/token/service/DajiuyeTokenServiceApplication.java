package com.lhf.dajiuye.token.service;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@DubboComponentScan(basePackages = "com.lhf.dajiuye.token.service.service.impl")
@EnableCaching
public class DajiuyeTokenServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeTokenServiceApplication.class, args);
    }

}
