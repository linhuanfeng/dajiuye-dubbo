package com.lhf.dajiuye.api.service.user;


import com.lhf.dajiuye.api.bean.LoginInfo;
import com.lhf.dajiuye.api.bean.User;
import com.lhf.dajiuye.api.dto.OpenIdTokenDto;

public interface WxService {
    User getUserInfo(LoginInfo loginInfo);
    String getOpenIdByCode(String code);
    OpenIdTokenDto getOpenIdToken(String code);
    String checkToken(String token);
}
