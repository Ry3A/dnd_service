package ru.aplk.dnd_service.authentication.routes.dto;

public class TokenDTO {
    private String color;

    public TokenDTO() {
    }

    public TokenDTO(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
