package com.hu.health.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户认证的filter，若认证成功，返回认证凭证Authentication
 * 但真正的认证逻辑是交给认证管理器authenticationManager，所以在创建本过滤器的时候需要设置认证管理器
 */
@Deprecated
public class WxAppletAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter{
    /**
     *
     * @param defaultFilterProcessesUrl 登录请求的url,
     *                                  如果请求的路径和url匹配，才会调用attemptAuthentication()进行认证
     */

    public WxAppletAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }



    /**
     * 认证的逻辑，并返回认证结果
     * 具体是通过openId调用微信api验证code是否成功
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String openId = request.getParameter("code");
//        request.get
return null;
//        return this.getAuthenticationManager().authenticate(new WxAuthenticationToken());
    }
}
