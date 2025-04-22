package ru.aplk.dnd_service.tables.classes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aplk.dnd_service.additions.ControllerFunctions;
import ru.aplk.dnd_service.additions.types.ResponseWithStatus;
import ru.aplk.dnd_service.additions.types.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassController {
    private final ControllerFunctions controllerFunctions;
    private final ClassService classService;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Class>>> findAll(){
        return ResponseEntity.ok(classService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<Class>> findById(@PathVariable Long id){
        return controllerFunctions.responseWithStatus(id, classService::findById);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Class education, HttpServletRequest request){
        return controllerFunctions.statusCode(education, classService::save, request);
    }
}
