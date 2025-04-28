package ru.aplk.dnd_service.tables.characters;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.aplk.dnd_service.users.User;

import java.util.List;

import java.util.Map;



@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    // Создать нового персонажа
    @PostMapping
    public Character createCharacter(@RequestBody Character character) {
        return characterService.createCharacter(character);
    }

    // Получить всех персонажей всех пользователей
    @GetMapping
    public List<Character> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    // Получить персонажей текущего пользователя по токену
    @GetMapping("/my")
    public List<Character> getCharactersOfCurrentUser() {
        return characterService.getCharactersOfCurrentUser();
    }

    // Получить персонажа по его id
    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable Long id) {
        return characterService.getCharacterById(id);
    }

    // --- Получение всех навыков персонажа ---
    @GetMapping("/{id}/skills")
    public Map<String, Integer> getAllSkills(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getAllSkills(character);
    }

    // --- Получение отдельных навыков персонажа ---
    @GetMapping("/{id}/sleightofhand")
    public int getSleightOfHand(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getSleightOfHand(character);
    }

    @GetMapping("/{id}/stealth")
    public int getStealth(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getStealth(character);
    }

    @GetMapping("/{id}/acrobatics")
    public int getAcrobatics(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getAcrobatics(character);
    }

    @GetMapping("/{id}/athletics")
    public int getAthletics(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getAthletics(character);
    }

    @GetMapping("/{id}/animalhandling")
    public int getAnimalHandling(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getAnimalHandling(character);
    }

    @GetMapping("/{id}/survival")
    public int getSurvival(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getSurvival(character);
    }

    @GetMapping("/{id}/perception")
    public int getPerception(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getPerception(character);
    }

    @GetMapping("/{id}/medicine")
    public int getMedicine(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getMedicine(character);
    }

    @GetMapping("/{id}/insight")
    public int getInsight(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getInsight(character);
    }

    @GetMapping("/{id}/deception")
    public int getDeception(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getDeception(character);
    }

    @GetMapping("/{id}/performance")
    public int getPerformance(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getPerformance(character);
    }

    @GetMapping("/{id}/persuasion")
    public int getPersuasion(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getPersuasion(character);
    }

    @GetMapping("/{id}/intimidation")
    public int getIntimidation(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getIntimidation(character);
    }

    @GetMapping("/{id}/history")
    public int getHistory(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getHistory(character);
    }

    @GetMapping("/{id}/nature")
    public int getNature(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getNature(character);
    }

    @GetMapping("/{id}/arcana")
    public int getArcana(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getArcana(character);
    }

    @GetMapping("/{id}/religion")
    public int getReligion(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getReligion(character);
    }

    @GetMapping("/{id}/investigation")
    public int getInvestigation(@PathVariable Long id) {
        Character character = characterService.getCharacterById(id);
        return characterService.getInvestigation(character);
    }
}


