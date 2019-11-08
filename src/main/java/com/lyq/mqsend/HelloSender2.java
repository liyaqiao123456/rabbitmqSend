package com.lyq.mqsend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyq.conf.TestProperty;
import com.lyq.constant.RabbitConstans;
import com.lyq.entity.UserDto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public void send1() throws JsonProcessingException {
        String sendMsg = "hello2 " + new Date();
        UserDto user1 = UserDto.builder()
                .id(48)
                .email("1123757388@qq.com")
                .post("adasdasd")
                .phone("18086509400")
                .sex(1)
                .build();
        List<UserDto> userList = new ArrayList();
        userList.add(user1);
        ObjectMapper mapper=new ObjectMapper();
        this.rabbitTemplate.convertAndSend(testProperty.getEmp().getExchange(),testProperty.getEmp().getRoutingKey(),mapper.writeValueAsString(userList),
                   message->{
                       message.getMessageProperties().setMessageId(UUID.randomUUID().toString());
                       message.getMessageProperties().setTimestamp(new Date());
                       return message;
                   }
        );
    }
}