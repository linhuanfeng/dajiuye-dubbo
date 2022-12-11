package com.lhf.dajiuye.message.service.service.impl;

import com.lhf.dajiuye.api.bean.chat.Message;
import com.lhf.dajiuye.api.bean.chat.MessageDetail;
import com.lhf.dajiuye.api.service.message.MessageService;
import com.lhf.dajiuye.message.service.mapper.MessageMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(interfaceClass = MessageService.class,version = "1.0.0")
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper mapper;

    @Override
    public void saveMessage(Message msg) {
        mapper.saveMessage(msg);
    }

    @Override
    public List<Message> getAllMessage(String openId) {
        List<Message> allMessage = mapper.getAllMessage(openId);
        return allMessage;
    }

    @Override
    public List<MessageDetail> getAllMessageDetail(String openId) {
        List<MessageDetail> messageDetail = mapper.getAllMessageDetail(openId);
        return messageDetail;
    }
}
