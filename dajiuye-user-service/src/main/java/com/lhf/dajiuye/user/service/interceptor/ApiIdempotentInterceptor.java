package com.lhf.dajiuye.user.service.interceptor;

import com.lhf.dajiuye.api.service.user.ApiIdempotentTokenService;
import com.lhf.dajiuye.user.service.annotation.ApiIdempotent;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

//@Component // 不能自动注入，因为创建此拦截器需要用到webmvcConfigure造成循环依赖
public class ApiIdempotentInterceptor implements HandlerInterceptor {

    ApiIdempotentTokenService apiIdempotentTokenService;

    public ApiIdempotentInterceptor(ApiIdempotentTokenService tokenService){
        this.apiIdempotentTokenService=tokenService;
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        ApiIdempotent methodAnnotation = method.getAnnotation(ApiIdempotent.class);
        if(methodAnnotation!=null){
            check(request);
        }
        return true;
    }
    private void check(HttpServletRequest request){
        apiIdempotentTokenService.checkToken(request);
    }
}
