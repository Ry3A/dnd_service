package ru.aplk.dnd_service.tables.games;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.aplk.dnd_service.users.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameNotificationService {

    private final GameRepository gameRepository;
    private final JavaMailSender mailSender;

    @Scheduled(cron = "0 0 9 * * ?") // каждый день в 9 утра
    public void notifyUpcomingGames() {
        LocalDate today = LocalDate.now();
        LocalDate targetDay = today.plusDays(2); // День игр через 2 дня

        LocalDateTime startOfDay = targetDay.atStartOfDay(); // 01.05.2025 00:00
        LocalDateTime endOfDay = targetDay.plusDays(1).atStartOfDay(); // 02.05.2025 00:00

        List<Game> upcomingGames = gameRepository.findAllByStartDateBetween(startOfDay, endOfDay);

        for (Game game : upcomingGames) {
            for (User player : game.getPlayers()) {
                sendNotificationEmail(player.getEmail(), game);
            }
        }
    }

    private void sendNotificationEmail(String to, Game game) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Напоминание об игре: " + game.getName());
        message.setText("Не забудьте! Игра \"" + game.getName() + "\" начнется " + game.getStartDate());

        mailSender.send(message);
    }
}
