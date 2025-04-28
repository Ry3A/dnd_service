package ru.aplk.dnd_service.tables.games;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aplk.dnd_service.users.User;
import ru.aplk.dnd_service.users.UserRepo;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepo userRepository;

    // Создание игры, привязка к пользователю
    public Game createGame(Game game) {
        String username = getCurrentUsername();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        game.setOwner(user);
        game.getPlayers().add(user);
        return gameRepository.save(game);
    }

    // Получить все игры
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    // Получить игру по ID
    public Game getGameById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + id));
    }

    // Получить игры текущего пользователя
    public List<Game> getGamesOfCurrentUser() {
        String username = getCurrentUsername();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return gameRepository.findByPlayersContaining(user);
    }

    // Присоединение к игре по токену

    public Game joinGame(Long gameId) {
        String username = getCurrentUsername();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Game game = getGameById(gameId);

        if (game.getPlayers().contains(user)) {
            throw new RuntimeException("User already joined the game");
        }

        if (game.getMaxPlayers() != null && game.getPlayers().size() >= game.getMaxPlayers()) {
            throw new RuntimeException("Game is full");
        }

        game.getPlayers().add(user);
        return gameRepository.save(game);
    }

    // Вспомогательный метод для получения текущего пользователя
    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        } else {
            return principal.toString();
        }
    }
}
