package ru.aplk.dnd_service.tables.backgrounds;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aplk.dnd_service.additions.ServiceFunctions;
import ru.aplk.dnd_service.additions.types.ResponseWithStatus;
import ru.aplk.dnd_service.additions.types.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BackgroundService {
    private final BackgroundRepository backgroundRepository;
    private final ServiceFunctions serviceFunctions;

    public ResponseWithStatus<List<Background>> findAll(){
        return ResponseWithStatus.create(200, backgroundRepository.findAll());
    }

    public List<Background> findAllWeb(){
        return backgroundRepository.findAll();
    }

    public ResponseWithStatus<Background> findById(Long id){
        return serviceFunctions.findBy(id, backgroundRepository::findById);
    }

    public StatusCode save(Background background, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                background,
                background.getName(),
                backgroundRepository::findByName,
                backgroundRepository::save,
                request
        );
    }

}
