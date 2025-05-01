package ru.aplk.dnd_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // клиент подключается сюда
                .setAllowedOriginPatterns("*")
                .withSockJS(); // включаем SockJS
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // мастер подписывается на /topic/game/...
        config.setApplicationDestinationPrefixes("/app"); // игроки отправляют на /app/...
    }
}
