package com.lhf.dajiuye.job.service;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lhf.dajiuye.job.service.mapper")
@DubboComponentScan("com.lhf.dajiuye.job.service.service.impl")
public class DajiuyeJobServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeJobServiceApplication.class, args);
    }

}
