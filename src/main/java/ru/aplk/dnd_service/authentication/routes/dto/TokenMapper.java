package ru.aplk.dnd_service.authentication.routes.dto;

import ru.aplk.dnd_service.tables.Token;

public class TokenMapper {
    public static TokenDTO toDto(Token token) {
        if (token == null) return null;
        TokenDTO dto = new TokenDTO();
        dto.setColor(token.getColor());
        return dto;
    }

    public static Token fromDto(TokenDTO dto) {
        if (dto == null) return null;
        Token token = new Token();
        token.setColor(dto.getColor());
        return token;
    }
}
