package ru.aplk.dnd_service.authentication.routes.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GridUpdateMessage {

    @JsonProperty("gameId")
    private Long gameId;

    @JsonProperty("updates") // ✅ обязательно!
    private List<CellUpdate> updates;

    public GridUpdateMessage() {}

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public List<CellUpdate> getUpdates() {
        return updates;
    }

    public void setUpdates(List<CellUpdate> updates) {
        this.updates = updates;
    }

    // ✅ обязательно static!
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CellUpdate {
        private int row;
        private int col;
        private GridCellDTO cell;

        public CellUpdate() {}

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public GridCellDTO getCell() {
            return cell;
        }

        public void setCell(GridCellDTO cell) {
            this.cell = cell;
        }
    }
}




