package com.lhf.dajiuye.api.service.message;



import com.lhf.dajiuye.api.bean.chat.Message;
import com.lhf.dajiuye.api.bean.chat.MessageDetail;

import java.util.List;

public interface MessageService {
    void saveMessage(Message msg);

    List<Message> getAllMessage(String openId);

    List<MessageDetail> getAllMessageDetail(String openId);
}
