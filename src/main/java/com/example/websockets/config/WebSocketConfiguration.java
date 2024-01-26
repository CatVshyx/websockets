package com.example.websockets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
         //        The registerStompEndpoints() method registers the /gs-guide-websocket endpoint for websocket connections.

        registry.addEndpoint("/gs-guide-websocket");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        https://spring.io/guides/gs/messaging-stomp-websocket/
//        to enable a simple memory-based message broker to carry the greeting messages back
//        to the client on destinations prefixed with /topic. It also designates the /app prefix
//        for messages that are bound for methods annotated with @MessageMapping. This prefix will be used
//        to define all the message mappings. For example, /app/hello is the endpoint that the GreetingController.greeting()
//        method is mapped to handle.
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
