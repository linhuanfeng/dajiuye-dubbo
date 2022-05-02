package com.lhf.dajiuye.web.app.controller.user;

import com.lhf.dajiuye.api.bean.Userdeliver;
import com.lhf.dajiuye.api.service.user.UserDeliverService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/deliver")
public class UserdeliverController {

    @DubboReference(interfaceClass = UserDeliverService.class,version = "1.0.0",check = false)
    UserDeliverService userDeliverService;
    /**
     * 用户发送简历
     * @param userdeliver
     * @return
     */
    @GetMapping(value = "/saveDeliver")
    public String saveDeliver(@ModelAttribute Userdeliver userdeliver){
        userDeliverService.saveDeliver(userdeliver);

        return "保存信息成功";
    }
    /**
     * 更新简历投递状态
     * @param userdeliver
     * @return
     */
    @GetMapping(value = "/updateDeliver")
    public String updateDeliver(@ModelAttribute Userdeliver userdeliver){
        userDeliverService.updateDeliver(userdeliver);
        return "保存信息成功";
    }
}
