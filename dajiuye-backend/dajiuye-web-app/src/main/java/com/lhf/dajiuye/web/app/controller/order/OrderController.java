package com.lhf.dajiuye.web.app.controller.order;

import com.lhf.dajiuye.api.bean.CommonResult2;
import com.lhf.dajiuye.api.bean.Meta;
import com.lhf.dajiuye.api.service.user.ApiIdempotent;
import com.lhf.dajiuye.api.service.user.ApiIdempotentTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.lhf.dajiuye.api.constant.IdempotentTokenConstants.API_IDEMPOTENT_TOKEN_REDIS_PREFIX;

@RestController
@RequestMapping("order/producer")
@Deprecated
public class OrderController {

    @Autowired
    private ApiIdempotentTokenService apiIdempotentTokenService;

    /**
     * 获取分布式全局id-->雪花算法
     * @return
     * @throws IOException
     */
    @RequestMapping("/snow")
    public Object snow(){
        return new CommonResult2<String>(String.valueOf(apiIdempotentTokenService.createToken()),new Meta("获取成功",200)) ;
    }

    /**
     * 校验token
     * @return
     * @throws IOException
     */
    @RequestMapping("/check")
    public Object check(@RequestParam(API_IDEMPOTENT_TOKEN_REDIS_PREFIX) String token){
        apiIdempotentTokenService.checkToken(token);
        return new CommonResult2<String>("校验成功",new Meta("获取成功",200)) ;
    }

    /**
     * 校验token
     * @return
     * @throws IOException
     */
    @RequestMapping("/hello")
    @ApiIdempotent
    public Object hello(HttpServletRequest request){
//        apiIdempotentTokenService.checkToken(request);
        return new CommonResult2<String>("校验成功",new Meta("获取成功",200)) ;
    }

}
