package com.lhf.dajiuye.api.service.message;

public interface KafkaService {
    void sendMessage(String message);
    void sendMessage(String topic,String message);
}
