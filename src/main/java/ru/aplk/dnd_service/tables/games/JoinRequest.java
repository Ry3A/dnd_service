package ru.aplk.dnd_service.tables.games;

import lombok.Data;

@Data
public class JoinRequest {
    private Long gameId;
    private Long characterId;
}