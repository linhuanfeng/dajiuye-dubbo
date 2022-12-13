package com.lhf.dajiuye.web.app.utils;

import com.lhf.dajiuye.api.constant.IdempotentTokenConstants;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.lhf.dajiuye.api.constant.IdempotentTokenConstants.API_IDEMPOTENT_TOKEN_NAME;

public class RequestUtil {
    /**
     * 获取请求头或请求体中的幂等token值
     * @param request
     * @return
     */
    public static String parseToken(HttpServletRequest request){
        String token = request.getHeader(API_IDEMPOTENT_TOKEN_NAME);
        if(StringUtils.isEmpty(token)){
            token=request.getParameter(API_IDEMPOTENT_TOKEN_NAME);
        }
        token= org.springframework.util.StringUtils.hasText(token)?token:"";
        return token;
    }
}
