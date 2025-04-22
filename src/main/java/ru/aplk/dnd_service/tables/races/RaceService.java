package ru.aplk.dnd_service.tables.races;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aplk.dnd_service.additions.ServiceFunctions;
import ru.aplk.dnd_service.additions.types.ResponseWithStatus;
import ru.aplk.dnd_service.additions.types.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RaceService {
    private final RaceRepository raceRepository;
    private final ServiceFunctions serviceFunctions;

    public ResponseWithStatus<List<Race>> findAll(){
        return ResponseWithStatus.create(200, raceRepository.findAll());
    }

    public List<Race> findAllWeb(){
        return raceRepository.findAll();
    }

    public ResponseWithStatus<Race> findById(Long id){
        return serviceFunctions.findBy(id, raceRepository::findById);
    }

    public StatusCode save(Race race, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                race,
                race.getName(),
                raceRepository::findByName,
                raceRepository::save,
                request
        );
    }

}
