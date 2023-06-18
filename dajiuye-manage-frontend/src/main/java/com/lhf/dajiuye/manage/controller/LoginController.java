package com.lhf.dajiuye.manage.controller;

import com.lhf.dajiuye.manage.entiity.User;
import com.lhf.dajiuye.manage.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @GetMapping("/login")
    public R update(){
        User user = new User();
        user.setId("213");
        user.setName("asdasda");
        List<User> list=new ArrayList<>();
        list.add(user);
        return R.ok().put("list",list);
    }
}
