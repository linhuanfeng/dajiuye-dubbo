package com.hu.health.security.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 用户登录信息的凭证封装（包括密码，用户名，权限列表，是否登录，token等，可自定义）
 */
public class WxAuthenticationToken extends AbstractAuthenticationToken {

    private String openId;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */


    public WxAuthenticationToken(String openId,Collection<? extends GrantedAuthority> authorities) {
        this(authorities);
        this.openId=openId;
    }

    public WxAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);
    }

    /**
     * 获取证书（密码）
     * @return
     */
    @Override
    public Object getCredentials() {
        return openId;
    }

    /**
     * 获取用户主体
     * @return
     */
    @Override
    public Object getPrincipal() {
        return openId;
    }
}
