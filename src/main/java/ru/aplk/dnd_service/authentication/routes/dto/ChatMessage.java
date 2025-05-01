package ru.aplk.dnd_service.authentication.routes.dto;

public class ChatMessage {
    private String gameId;
    private String type; // "chat" или "initiative"
    private String sender;
    private String content;

    // Геттеры и сеттеры
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
