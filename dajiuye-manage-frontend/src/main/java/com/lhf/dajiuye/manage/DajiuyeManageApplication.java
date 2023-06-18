package com.lhf.dajiuye.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.lhf.dajiuye.manage.dao")
public class DajiuyeManageApplication {
//
    public static void main(String[] args) {
        SpringApplication.run(DajiuyeManageApplication.class, args);
    }

}
