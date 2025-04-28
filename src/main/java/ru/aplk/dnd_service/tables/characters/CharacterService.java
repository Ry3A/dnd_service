package ru.aplk.dnd_service.tables.characters;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aplk.dnd_service.authentication.jwt.JwtService;
import ru.aplk.dnd_service.users.User;
import ru.aplk.dnd_service.users.UserRepo;

import java.util.List;


import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final UserRepo userRepository;


    public Character createCharacter(Character character) {
        // Получаем текущего пользователя через SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Ищем пользователя по email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        // Добавляем персонажа в список персонажей пользователя
        user.getCharacters().add(character);

        // Сохраняем пользователя вместе с новым персонажем
        userRepository.save(user);

        return character;
    }

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Character getCharacterById(Long id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found"));
    }

    public List<Character> getCharactersOfCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        return user.getCharacters();
    }

    // Получение всех навыков персонажа
    public Map<String, Integer> getAllSkills(Character character) {
        Map<String, Integer> skills = new HashMap<>();

        skills.put("Acrobatics", calculateSkill(character.getDexterity(), character.isProficientInAcrobatics(), character.getProficiencyBonus()));
        skills.put("AnimalHandling", calculateSkill(character.getWisdom(), character.isProficientInAnimalHandling(), character.getProficiencyBonus()));
        skills.put("Arcana", calculateSkill(character.getIntelligence(), character.isProficientInArcana(), character.getProficiencyBonus()));
        skills.put("Athletics", calculateSkill(character.getStrength(), character.isProficientInAthletics(), character.getProficiencyBonus()));
        skills.put("Deception", calculateSkill(character.getCharisma(), character.isProficientInDeception(), character.getProficiencyBonus()));
        skills.put("History", calculateSkill(character.getIntelligence(), character.isProficientInHistory(), character.getProficiencyBonus()));
        skills.put("Insight", calculateSkill(character.getWisdom(), character.isProficientInInsight(), character.getProficiencyBonus()));
        skills.put("Intimidation", calculateSkill(character.getCharisma(), character.isProficientInIntimidation(), character.getProficiencyBonus()));
        skills.put("Investigation", calculateSkill(character.getIntelligence(), character.isProficientInInvestigation(), character.getProficiencyBonus()));
        skills.put("Medicine", calculateSkill(character.getWisdom(), character.isProficientInMedicine(), character.getProficiencyBonus()));
        skills.put("Nature", calculateSkill(character.getIntelligence(), character.isProficientInNature(), character.getProficiencyBonus()));
        skills.put("Perception", calculateSkill(character.getWisdom(), character.isProficientInPerception(), character.getProficiencyBonus()));
        skills.put("Performance", calculateSkill(character.getCharisma(), character.isProficientInPerformance(), character.getProficiencyBonus()));
        skills.put("Persuasion", calculateSkill(character.getCharisma(), character.isProficientInPersuasion(), character.getProficiencyBonus()));
        skills.put("Religion", calculateSkill(character.getIntelligence(), character.isProficientInReligion(), character.getProficiencyBonus()));
        skills.put("SleightOfHand", calculateSkill(character.getDexterity(), character.isProficientInSleightOfHand(), character.getProficiencyBonus()));
        skills.put("Stealth", calculateSkill(character.getDexterity(), character.isProficientInStealth(), character.getProficiencyBonus()));
        skills.put("Survival", calculateSkill(character.getWisdom(), character.isProficientInSurvival(), character.getProficiencyBonus()));

        return skills;
    }

    // Методы для получения отдельных навыков
    public int getSleightOfHand(Character character) {
        return calculateSkill(character.getDexterity(), character.isProficientInSleightOfHand(), character.getProficiencyBonus());
    }

    public int getStealth(Character character) {
        return calculateSkill(character.getDexterity(), character.isProficientInStealth(), character.getProficiencyBonus());
    }

    public int getAcrobatics(Character character) {
        return calculateSkill(character.getDexterity(), character.isProficientInAcrobatics(), character.getProficiencyBonus());
    }

    public int getAthletics(Character character) {
        return calculateSkill(character.getStrength(), character.isProficientInAthletics(), character.getProficiencyBonus());
    }

    public int getAnimalHandling(Character character) {
        return calculateSkill(character.getWisdom(), character.isProficientInAnimalHandling(), character.getProficiencyBonus());
    }

    public int getSurvival(Character character) {
        return calculateSkill(character.getWisdom(), character.isProficientInSurvival(), character.getProficiencyBonus());
    }

    public int getPerception(Character character) {
        return calculateSkill(character.getWisdom(), character.isProficientInPerception(), character.getProficiencyBonus());
    }

    public int getMedicine(Character character) {
        return calculateSkill(character.getWisdom(), character.isProficientInMedicine(), character.getProficiencyBonus());
    }

    public int getInsight(Character character) {
        return calculateSkill(character.getWisdom(), character.isProficientInInsight(), character.getProficiencyBonus());
    }

    public int getDeception(Character character) {
        return calculateSkill(character.getCharisma(), character.isProficientInDeception(), character.getProficiencyBonus());
    }

    public int getPerformance(Character character) {
        return calculateSkill(character.getCharisma(), character.isProficientInPerformance(), character.getProficiencyBonus());
    }

    public int getPersuasion(Character character) {
        return calculateSkill(character.getCharisma(), character.isProficientInPersuasion(), character.getProficiencyBonus());
    }

    public int getIntimidation(Character character) {
        return calculateSkill(character.getCharisma(), character.isProficientInIntimidation(), character.getProficiencyBonus());
    }

    public int getHistory(Character character) {
        return calculateSkill(character.getIntelligence(), character.isProficientInHistory(), character.getProficiencyBonus());
    }

    public int getNature(Character character) {
        return calculateSkill(character.getIntelligence(), character.isProficientInNature(), character.getProficiencyBonus());
    }

    public int getArcana(Character character) {
        return calculateSkill(character.getIntelligence(), character.isProficientInArcana(), character.getProficiencyBonus());
    }

    public int getReligion(Character character) {
        return calculateSkill(character.getIntelligence(), character.isProficientInReligion(), character.getProficiencyBonus());
    }

    public int getInvestigation(Character character) {
        return calculateSkill(character.getIntelligence(), character.isProficientInInvestigation(), character.getProficiencyBonus());
    }

    // --- Вспомогательные методы ---

    // Вычисляем модификатор характеристики
    private int calculateModifier(int statValue) {
        return (statValue - 10) / 2;
    }

    // Вычисляем навык: модификатор + бонус владения, если владеем
    private int calculateSkill(int statValue, boolean isProficient, int proficiencyBonus) {
        int modifier = calculateModifier(statValue);
        return isProficient ? modifier + proficiencyBonus : modifier;
    }

    // Получаем текущего пользователя из токена
    private String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        } else {
            return principal.toString();
        }
    }
}
