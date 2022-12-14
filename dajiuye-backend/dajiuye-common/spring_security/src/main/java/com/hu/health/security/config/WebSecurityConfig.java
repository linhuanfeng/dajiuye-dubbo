package com.hu.health.security.config;

import com.hu.health.security.filter.*;
import com.hu.health.security.filter.JWTAuthenticationFilter;
import com.hu.health.security.security.DefaultPasswordEncoder;
import com.hu.health.security.security.TokenLogoutHandler;
import com.hu.health.security.security.TokenManager;
import com.hu.health.security.security.UnauthEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * springSecurity的全局配置类，
 * （1）自定义的MyUsernamePasswordAuthenticationFilter:
 *                      修改unsuccessfulAuthentication()--》认证成功方法，
 *                                 （1）把用户名：权限列表 存到redis中
 *                                 （2）把token响应回去
 * （2）自定义MyBasicAuthenticationFilter:
 *                      修改doFilterInternal  --》授权逻辑
 *                                  (1)根据用户名从redis中 获取权限列表
 * （3）使用自定义的userDetailService
 */
@Configuration // 这个配置也要被扫才有用
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 用自己的认证管理器（）认证账号和密码
//    @Autowired
    private TokenManager tokenManager;

//    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
    private DefaultPasswordEncoder defaultPasswordEncoder;

    /**
     * 重写loadUserByUsername，实现从数据库查数据，因此需要自定义实现这个
     */
//    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    public TokenWebSecurityConfig() {
//    }

    /**
     * 配置设置
     * @param http
     * @throws Exception
     */
    //设置退出的地址和token，redis操作地址
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/error/**").permitAll()
//                .anyRequest().authenticated()
//                .and();
//        http.addFilterAt(jwtOnePerRequestFilter(), UsernamePasswordAuthenticationFilter.class); // 替换
//                .addFilterBefore(jwtOnePerRequestFilter(),JWTOnePerRequestFilter.class);

        http.exceptionHandling()
                // 认证入口端点
//                .authenticationEntryPoint(new UnauthEntryPoint())//没有权限访问
                .and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user/wx/**").permitAll() // 登录不用拦截
                .antMatchers("/user/oss/**").permitAll()
                .antMatchers("/job/job/es/**").permitAll()
                /*swagger*/
                .antMatchers("/swagger-ui.html").permitAll() // 任意访问
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/api/**").permitAll()
//                .antMatchers("/own/user/handleWXMsg").anonymous()
//                .antMatchers("/acl/user/save").anonymous()
//                .antMatchers("/own/home/swiperdata").anonymous()
                .anyRequest().authenticated();
//        http.addFilterAt(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 在UsernamePasswordAuthenticationFilter前面添加用于验证jwt，识别用户是否登录的过滤器,有登录直接认证
                http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//                .and().logout().logoutUrl("/logout")//退出路径
//                .addLogoutHandler(new TokenLogoutHandler(tokenManager,redisTemplate));
//        http.addFilterAt(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 替换
//                .addFilterBefore(jwtOnePerRequestFilter(),JWTOnePerRequestFilter.class);
//                .addFilter(new MyUsernamePasswordAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate))
//                .addFilter(new MyBasicAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate)).httpBasic();
    }


    //调用userDetailsService和密码处理
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }
    //不进行认证的路径，可以直接访问
    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring()
//                // 不用经过basicAuth
//                .antMatchers("/own/user/handleWXMsg/**") //用户登录也不需要拦截
//                .antMatchers("/acl/user/save/**") //新增用户不拦截
//                .antMatchers("/own/home/swiperdata/**"); //轮播图数据
//        web.ignoring().antMatchers("/own/user/handleWXMsg");
//        web.ignoring().antMatchers("/acl/user/save");
//        web.ignoring().antMatchers("/own/home/swiperdata");
//        web.ignoring().antMatchers("/login","/logout");
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
    }

    @Autowired
    private AuthenticationManager manager;

    @Bean
    public WxAppletAuthenticationProcessingFilter wxAppletAuthenticationFilter(){
        WxAppletAuthenticationProcessingFilter filter = new WxAppletAuthenticationProcessingFilter("/login");
        filter.setAuthenticationManager(manager);
        return filter;
    }
}
