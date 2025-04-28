package ru.aplk.dnd_service.tables.characters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    // Здесь больше ничего не надо! Только базовые методы CRUD
}