package com.lhf.dajiuye.token.service.service.impl;

import com.lhf.dajiuye.api.service.user.ApiIdempotentTokenService;
import com.lhf.dajiuye.api.constant.IdempotentTokenConstants;
import com.lhf.dajiuye.token.service.strategy.TokenStrategyContext;
import com.lhf.dajiuye.token.service.utils.SnowflakeIdGenerator;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpServletRequest;

import static com.lhf.dajiuye.api.constant.IdempotentTokenConstants.API_IDEMPOTENT_TOKEN_REDIS_PREFIX;

/**
 * 生成token:雪花id
 * 同时存入redis:  key:ApiIdempotentTokenRedis:token  value:token  =>所以dubbo服务的超时时间要久一点
 * 校验token
 */
@DubboService(interfaceClass = ApiIdempotentTokenService.class,version = "1.0.0",timeout = 12000)
public class ApiIdempotentTokenServiceImpl implements ApiIdempotentTokenService {

    @Autowired
    private TokenStrategyContext tokenStrategyContext;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * token:雪花id
     * 同时存入redis:  key:ApiIdempotentTokenRedis:token  value:token
     * @return token
     */
    @Override
    public String createToken() {
        long token = SnowflakeIdGenerator.getInstance().nextId();
        redisTemplate.opsForValue().set(API_IDEMPOTENT_TOKEN_REDIS_PREFIX+token,String.valueOf(token));
        return String.valueOf(token);
    }

    @Override
    public void checkToken(String token) {
        if(token==null)
            throw new RuntimeException("失败，未携带token");
        boolean check = tokenStrategyContext.checkToken(API_IDEMPOTENT_TOKEN_REDIS_PREFIX+token);
        if(!check){
            throw new RuntimeException("token无效或已发送成功，请勿重复发送");
        }
    }
}
