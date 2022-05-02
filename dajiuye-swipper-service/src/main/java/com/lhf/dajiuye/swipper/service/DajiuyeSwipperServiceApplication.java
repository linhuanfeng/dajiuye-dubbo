package com.lhf.dajiuye.swipper.service;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan("com.lhf.dajiuye.swipper.service.service.impl")
public class DajiuyeSwipperServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeSwipperServiceApplication.class, args);
    }

}
