package ru.aplk.dnd_service.tables.backgrounds;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aplk.dnd_service.additions.ControllerFunctions;
import ru.aplk.dnd_service.additions.types.ResponseWithStatus;
import ru.aplk.dnd_service.additions.types.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/background")
@RequiredArgsConstructor
public class BackgroundController {
    private final ControllerFunctions controllerFunctions;
    private final BackgroundService backgroundService;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Background>>> findAll(){
        return ResponseEntity.ok(backgroundService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<Background>> findById(@PathVariable Long id){
        return controllerFunctions.responseWithStatus(id, backgroundService::findById);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Background education, HttpServletRequest request){
        return controllerFunctions.statusCode(education, backgroundService::save, request);
    }
}
