package ru.aplk.dnd_service.tables.classes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aplk.dnd_service.additions.ServiceFunctions;
import ru.aplk.dnd_service.additions.types.ResponseWithStatus;
import ru.aplk.dnd_service.additions.types.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassService {
    private final ClassRepository classRepository;
    private final ServiceFunctions serviceFunctions;

    public ResponseWithStatus<List<Class>> findAll(){
        return ResponseWithStatus.create(200, classRepository.findAll());
    }

    public List<Class> findAllWeb(){
        return classRepository.findAll();
    }

    public ResponseWithStatus<Class> findById(Long id){
        return serviceFunctions.findBy(id, classRepository::findById);
    }

    public StatusCode save(Class aClass, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                aClass,
                aClass.getName(),
                classRepository::findByName,
                classRepository::save,
                request
        );
    }

}
