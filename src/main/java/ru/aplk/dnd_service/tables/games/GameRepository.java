package ru.aplk.dnd_service.tables.games;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aplk.dnd_service.tables.classes.Class;
import ru.aplk.dnd_service.users.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Class> findByName(String name);
    List<Game> findByPlayersContaining(User user);
    List<Game> findAllByStartDateBetween(LocalDateTime start, LocalDateTime end);

}
