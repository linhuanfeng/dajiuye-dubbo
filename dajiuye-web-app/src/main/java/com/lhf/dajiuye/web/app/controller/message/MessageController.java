package com.lhf.dajiuye.web.app.controller.message;

import com.lhf.dajiuye.api.bean.*;
import com.lhf.dajiuye.api.service.message.MessageService;
import com.lhf.dajiuye.api.service.user.MyUserService;
import com.lhf.dajiuye.web.app.utils.MessageUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message/message")
public class MessageController {

    @DubboReference(interfaceClass = MessageService.class,version = "1.0.0",check = false)
    private MessageService messageService;

    @DubboReference(interfaceClass = MyUserService.class,version = "1.0.0",check = false)
    MyUserService userService;

    /**
     * 通过fromOpenId,toOpenId,news保存用户消息
     * @param msg
     * @return
     */
    @PostMapping(value = "/saveMessage")
    public String saveMessage(@RequestBody Message msg){
        messageService.saveMessage(msg);
        return "保存信息成功";
    }

    /**
     * 通过fromOpenId,userId,news保存用户消息
     * @param msg
     * @return
     */
    @PostMapping(value = "/saveMessage2")
    public String saveMessage2(@RequestBody Message msg){
        // 解谜出fromOpenId
        User user = userService.getUserById(String.valueOf(msg.getToOpenId()));
        msg.setToOpenId(user.getOpenId()); // 将id转为对应openId
        messageService.saveMessage(msg);
        return "保存信息成功";
    }

    /**
     * 查询某个openId对应的所有消息
     * @param openId
     * @return
     */
    @GetMapping(value = "/getAllMessage")
//    @OpenIdHandle
    public CommonResult2 getAllMessage(@RequestParam("openId") String openId){
        List<MessageDetail> messages = messageService.getAllMessageDetail(openId);
        // 这个列表只是查询到的所有信息，还得把同一个人的信息放在一起
        return new CommonResult2<List>(MessageUtil.mergeMsg(messages,openId),new Meta("获取成功",200));
    }
}
