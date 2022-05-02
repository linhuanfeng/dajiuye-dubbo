package com.lhf.dajiuye.api.service.user;

//import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInfo;
import com.lhf.dajiuye.api.bean.Params2;
import com.lhf.dajiuye.api.bean.User;

public interface MyUserService {

    User getUserByOpenId(String openId);

    User getUserById(String id);

    PageInfo<User> getUsers(Params2 params2);

    int saveUser(User user);

    int updateUser(User user);

    int updateUserResumeByOpenId(String resumeUrl, String openId);

}
