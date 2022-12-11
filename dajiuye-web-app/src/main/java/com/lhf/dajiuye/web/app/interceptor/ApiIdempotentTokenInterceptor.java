package com.lhf.dajiuye.web.app.interceptor;

import com.lhf.dajiuye.api.service.user.ApiIdempotent;
import com.lhf.dajiuye.api.service.user.ApiIdempotentTokenService;
import com.lhf.dajiuye.web.app.utils.RequestUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 接口幂等拦截器
 * 获取请求头apiIdempotentToken的值到redis中进行幂等验证，验证请求是否已处理过，已处理过的请求则失败
 */
// 不能自动注入，因为创建此拦截器需要用到webmvcConfigure造成循环依赖
public class ApiIdempotentTokenInterceptor implements HandlerInterceptor {
    private ApiIdempotentTokenService apiIdempotentTokenService;

    public ApiIdempotentTokenInterceptor(ApiIdempotentTokenService apiIdempotentTokenService) {
        this.apiIdempotentTokenService = apiIdempotentTokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 非controller请求直接放行
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        // 非标注@ApiIdempotent放行
        HandlerMethod method = (HandlerMethod) handler;
        ApiIdempotent apiIdempotent = method.getMethodAnnotation(ApiIdempotent.class);
        if(apiIdempotent==null){
            return true;
        }
        try {
            apiIdempotentTokenService.checkToken(RequestUtil.parseToken(request)); // 验证失败抛异常
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
