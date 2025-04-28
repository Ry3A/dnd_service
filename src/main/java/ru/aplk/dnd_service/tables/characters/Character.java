package ru.aplk.dnd_service.tables.characters;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import ru.aplk.dnd_service.tables.games.Game;

@Entity
@Table(name = "character")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(name = "background_id")
    private Long background_id;
    @Column(name = "race_id")
    private Long race_id;
    @Column(name = "class_id")
    private Long class_id;

    @ManyToOne
    @JoinColumn(name = "game_id") // В таблице персонажей будет колонка game_id
    private Game game;

    private String alignment;

    // Основные характеристики
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    // Бонус владения
    private int proficiencyBonus;

    // Владение навыками (новые поля)
    private boolean proficientInAcrobatics;
    private boolean proficientInAnimalHandling;
    private boolean proficientInArcana;
    private boolean proficientInAthletics;
    private boolean proficientInDeception;
    private boolean proficientInHistory;
    private boolean proficientInInsight;
    private boolean proficientInIntimidation;
    private boolean proficientInInvestigation;
    private boolean proficientInMedicine;
    private boolean proficientInNature;
    private boolean proficientInPerception;
    private boolean proficientInPerformance;
    private boolean proficientInPersuasion;
    private boolean proficientInReligion;
    private boolean proficientInSleightOfHand;
    private boolean proficientInStealth;
    private boolean proficientInSurvival;

    // Боевая информация
    private int level;
    private int experiencePoints;
    private int armorClass;
    private int initiative;
    private int speed;
    private int currentHitPoints;
    private int maxHitPoints;
    private int temporaryHitPoints;
    private int hitDice;
    private int deathSavesSuccesses;
    private int deathSavesFailures;

    // Дополнительные параметры
    private boolean inspiration;
    private int passivePerception;
    private int exhaustionLevel;
}
