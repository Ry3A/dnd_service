package ru.aplk.dnd_service.authentication.routes.dto;

import java.util.Map;

public class GridCellDTO {
    private Map<String, Boolean> walls;
    private TokenDTO token;

    public GridCellDTO() {}

    public Map<String, Boolean> getWalls() { return walls; }
    public void setWalls(Map<String, Boolean> walls) { this.walls = walls; }

    public TokenDTO getToken() { return token; }
    public void setToken(TokenDTO token) { this.token = token; }
}

