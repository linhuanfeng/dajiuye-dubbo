package com.lhf.dajiuye.api.service.user;

import javax.servlet.http.HttpServletRequest;

public interface ApiIdempotentTokenService {
    long createToken();
    void checkToken(HttpServletRequest request);
}
