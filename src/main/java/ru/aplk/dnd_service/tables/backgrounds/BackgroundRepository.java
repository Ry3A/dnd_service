package ru.aplk.dnd_service.tables.backgrounds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BackgroundRepository extends JpaRepository<Background, Long> {
    Optional<Background> findByName(String name);
}
