package com.lhf.dajiuye.job.service;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(value = {"com.lhf.dajiuye.job.service.mapper"})
@DubboComponentScan({"com.lhf.dajiuye.job.service.service.impl"})
@EnableCaching
public class DajiuyeJobServiceApplication {

    @Value("${spring.redis.host}")
    public static String profile;

    public static void main(String[] args) {
        System.out.println("sadadad:"+profile);
        SpringApplication.run(DajiuyeJobServiceApplication.class, args);
    }

}
