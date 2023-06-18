package com.lhf.dajiuye.acl.service;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lhf.dajiuye.acl.service")
@MapperScan("com.lhf.dajiuye.acl.service.mapper")
@DubboComponentScan("com.lhf.dajiuye.acl.service.service.impl")
public class DajiuyeAclServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeAclServiceApplication.class, args);
    }

}
