package ru.aplk.dnd_service.authentication.routes.dto;

import ru.aplk.dnd_service.authentication.routes.dto.GridCellDTO;
import ru.aplk.dnd_service.authentication.routes.dto.TokenDTO;
import ru.aplk.dnd_service.tables.GridCell;
import ru.aplk.dnd_service.tables.Token;

import java.util.*;
import java.util.stream.Collectors;

public class GridMapper {
    //Map<String, Boolean> walls = Optional.ofNullable(dto.getWalls()).orElse(Collections.emptyMap());

    public static GridCellDTO toDto(GridCell cell) {
        GridCellDTO dto = new GridCellDTO();

        Map<String, Boolean> walls = new HashMap<>();
        walls.put("top", cell.isTop());
        walls.put("right", cell.isRight());
        walls.put("bottom", cell.isBottom());
        walls.put("left", cell.isLeft());

        dto.setWalls(walls);
        dto.setToken(TokenMapper.toDto(cell.getToken()));
        return dto;
    }



    public static GridCell fromDto(GridCellDTO dto) {
        GridCell cell = new GridCell();

        Map<String, Boolean> walls = Optional.ofNullable(dto.getWalls())
                .orElse(Collections.emptyMap());

        cell.setTop(walls.getOrDefault("top", false));
        cell.setRight(walls.getOrDefault("right", false));
        cell.setBottom(walls.getOrDefault("bottom", false));
        cell.setLeft(walls.getOrDefault("left", false));

        cell.setToken(dto.getToken() != null ? TokenMapper.fromDto(dto.getToken()) : null);
        return cell;
    }

    public static List<List<GridCellDTO>> toDtoGrid(List<List<GridCell>> grid) {
        return grid.stream()
                .map(row -> row.stream().map(GridMapper::toDto).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static List<List<GridCell>> fromDtoGrid(List<List<GridCellDTO>> dtoGrid) {
        return dtoGrid.stream()
                .map(row -> row.stream().map(GridMapper::fromDto).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
