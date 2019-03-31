package com.lyq.controller;

import com.lyq.mqsend.HelloSender1;
import com.lyq.mqsend.HelloSender2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitTest {

    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender2 helloSender2;

    @GetMapping("/hello1")
    public void hello1() {
        helloSender1.send();
    }

    @GetMapping("/hello2")
    public void hello2() {
        helloSender2.send();
    }

    @GetMapping("/hello3")
    public void hello3() {
        helloSender2.send1();
    }
}
