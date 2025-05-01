package ru.aplk.dnd_service.config;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.aplk.dnd_service.authentication.routes.dto.ChatMessage;

@Controller
public class WebSocketController {

    @MessageMapping("/game/message")
    @SendTo("/topic/game") // Отправляем всем, кто подписан на /topic/game
    public ChatMessage handleMessage(@Payload ChatMessage message) {
        return message; // просто пересылаем дальше
    }
}
