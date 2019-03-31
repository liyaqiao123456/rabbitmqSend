package com.lyq.mqsend;

import com.lyq.conf.TestProperty;
import com.lyq.constant.RabbitConstans;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * RabbitTemplate
 */
@Component
public class HelloSender2 {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TestProperty testProperty;

    public void send() {
        String sendMsg = "hello1" + new Date();
        System.out.println("Sender1 : " + sendMsg);
        this.rabbitTemplate.convertAndSend(RabbitConstans.TEST_QUEUE, sendMsg);
    }

    public void send1() {
        String sendMsg = "hello2 " + new Date();
        this.rabbitTemplate.convertAndSend(testProperty.getMq().getExchange(),testProperty.getMq().getRoutingKey(),sendMsg,
                   message->{
                       message.getMessageProperties().setMessageId(UUID.randomUUID().toString());
                       message.getMessageProperties().setTimestamp(new Date());
                       return message;
                   }
        );
    }
}