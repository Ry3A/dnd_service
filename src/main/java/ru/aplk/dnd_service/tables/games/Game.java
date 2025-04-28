package ru.aplk.dnd_service.tables.games;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.aplk.dnd_service.users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;


@Entity
@Table(name = "game") // Обычно таблицу называют во множественном числе
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner; // Создатель игры (мастер)

    @ManyToMany
    @JoinTable(
            name = "game_players",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> players = new ArrayList<>();

    private LocalDateTime dateTime;

    private Integer maxPlayers; // Ограничение по количеству игроков
}
