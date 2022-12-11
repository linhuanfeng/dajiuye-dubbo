package com.lhf.dajiuye.web.app.config;

import com.lhf.dajiuye.api.service.user.ApiIdempotentTokenService;
import com.lhf.dajiuye.web.app.interceptor.ApiIdempotentTokenInterceptor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(AliYunOssConfig.class)
public class WebMvcConfig implements WebMvcConfigurer {

    @DubboReference(interfaceClass = ApiIdempotentTokenService.class,version = "1.0.0",check = false)
    private ApiIdempotentTokenService apiIdempotentTokenService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiIdempotentTokenInterceptor(apiIdempotentTokenService)).addPathPatterns("/**");
    }
}
