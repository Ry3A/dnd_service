package ru.aplk.dnd_service.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import ru.aplk.dnd_service.authentication.routes.dto.ChatMessage;

import ru.aplk.dnd_service.authentication.routes.dto.GridMapper;
import ru.aplk.dnd_service.authentication.routes.dto.GridUpdateMessage;
import ru.aplk.dnd_service.tables.GridCell;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/game/message")
    @SendTo("/topic/game")
    public ChatMessage handleMessage(@Payload ChatMessage message) {
        return message;
    }
    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    /*@MessageMapping("/game/{gameId}/grid")
    public void handleGrid(@DestinationVariable Long gameId, @Payload GridUpdateMessage message) {
        System.out.println("📥 GRID получен от клиента. Игра: " + gameId);
        messagingTemplate.convertAndSend("/topic/game/" + gameId + "/grid", message);
    }
    */

    @MessageMapping("/game/{gameId}/grid")
    public void handleGrid(@DestinationVariable Long gameId, @Payload GridUpdateMessage message) {
        if (message.getUpdates() == null) {
            System.out.println("⚠️ Получено сообщение без updates (null)");
            return;
        }

        System.out.println("📥 GRID получен. Игра: " + gameId + ", обновлений: " + message.getUpdates().size());
        messagingTemplate.convertAndSend("/topic/game/" + gameId + "/grid", message);
    }



    @MessageExceptionHandler
    public void handleException(Exception ex) {
        System.out.println("❌ Ошибка обработки сообщения: " + ex.getMessage());
        ex.printStackTrace();
    }



}
