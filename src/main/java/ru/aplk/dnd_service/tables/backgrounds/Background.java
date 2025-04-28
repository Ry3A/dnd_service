package ru.aplk.dnd_service.tables.backgrounds;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.Lob;

@Entity
@Table(name = "background")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Background {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;
    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
}
