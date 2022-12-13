package com.hu.health.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 真正认证干活的经理，需要设置到认证过滤器(WxAppletAuthenticationProcessingFilter)中
 */
@Component
public class WxAppletAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return new WxAuthenticationToken("adasd",null);
    }
}
