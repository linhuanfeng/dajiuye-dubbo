package com.lhf.dajiuye.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.lhf.dajiuye.web.app","com.hu.health.security"}) // 扫指定的包
public class DajiuyeWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeWebAppApplication.class, args);
    }

}
