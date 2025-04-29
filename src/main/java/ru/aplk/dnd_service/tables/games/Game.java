package ru.aplk.dnd_service.tables.games;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.aplk.dnd_service.users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import ru.aplk.dnd_service.tables.characters.Character;
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

    @OneToMany(mappedBy = "game")
    private List<Character> characters = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_players",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> players = new ArrayList<>();

    @Column(name = "start_date")
    private LocalDateTime startDate;

    private Integer maxPlayers; // Ограничение по количеству игроков
}
