package ru.aplk.dnd_service.tables.races;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "race")
@Embeddable
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;

    private String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
}
