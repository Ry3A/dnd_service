package ru.aplk.dnd_service.tables.games;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    // Создание новой игры
    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    // Получение всех игр
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/my")
    public List<Game> getMyGames() {
        return gameService.getGamesOfCurrentUser();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable Long id) {
        return gameService.getGameById(id);
    }

    // Присоединение к игре текущего пользователя
    @PostMapping("/{gameId}/join")
    public Game joinGame(@PathVariable Long gameId) {
        return gameService.joinGame(gameId);
    }
}
