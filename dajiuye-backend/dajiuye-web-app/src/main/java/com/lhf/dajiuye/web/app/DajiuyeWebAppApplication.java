package com.lhf.dajiuye.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableCaching // 开启缓存
@EnableSwagger2
@SpringBootApplication(scanBasePackages = {"com.lhf.dajiuye.web.app","com.hu.health.security"}) // 扫指定的包
public class DajiuyeWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DajiuyeWebAppApplication.class, args);
    }

}
