package com.devi.test.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Package MsgConsumer
 * @Description: 消息消费者
 * @Copyright: Copyright (c) 2016
 *
 * @date 2017/5/18 16:15
 * version V1.0.0
 */
@Component
public class MsgConsumer {

    @KafkaListener(topics = {"jon-replicated-topic","jon-replicated-topic2"})
    public void processMessage(String content) {
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$" + content);
    }
}
