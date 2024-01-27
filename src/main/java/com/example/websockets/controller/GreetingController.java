package com.example.websockets.controller;

import com.example.websockets.model.Greeting;
import com.example.websockets.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting getGreeting(HelloMessage message) throws InterruptedException {
        System.out.println(message);
        Thread.sleep(1000);
        return new Greeting(message.getName(),message.getMessage());
    }
}
