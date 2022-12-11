package com.lhf.dajiuye.seckill;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan("com.lhf.dajiuye.seckill.service.impl")
@MapperScan("com.lhf.dajiuye.seckill.dao")
public class DajiuyeSecKillServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeSecKillServiceApplication.class, args);
    }

}
