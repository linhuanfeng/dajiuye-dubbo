package com.hu.health.security.filter;

import com.lhf.dajiuye.api.service.acl.PermissionService;
import com.lhf.dajiuye.api.service.user.MyUserService;
import com.lhf.dajiuye.api.service.user.WxService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 替换原生UsernamePasswordAuthenticationFilter
 * 校验请求头jwt是否有效，以此为依据判断是否登录，若已登录，直接登录信息放到security上下文中
 */
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @DubboReference(interfaceClass = PermissionService.class,version = "1.0.0",check = false)
    private PermissionService permissionService;

    @DubboReference(interfaceClass = WxService.class,version = "1.0.0",check = false)
    private WxService wxService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.hasText(token)) {
            // 校验token判断登录状态
            String openId = wxService.checkToken(token); // 校验token成功能直接获取openId
            // token通过，直接授权
            List<String> permissions = permissionService.selectPermissionValueByUserId(openId);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                // 根据openId获取权限列表
                log.debug("security context was null, so authorizing user");
                List<SimpleGrantedAuthority> authorities = permissions.stream().map(p -> new SimpleGrantedAuthority(p)).collect(Collectors.toList());
                log.info("当前用户的权限列表为：{}",authorities.toString());
                SecurityContextHolder.getContext().setAuthentication(new WxAuthenticationToken(openId, authorities));
            }
        }
        filterChain.doFilter(request, response);
    }
}
