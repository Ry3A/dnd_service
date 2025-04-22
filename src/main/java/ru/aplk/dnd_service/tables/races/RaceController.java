package ru.aplk.dnd_service.tables.races;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aplk.dnd_service.additions.ControllerFunctions;
import ru.aplk.dnd_service.additions.types.ResponseWithStatus;
import ru.aplk.dnd_service.additions.types.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/race")
@RequiredArgsConstructor
public class RaceController {
    private final ControllerFunctions controllerFunctions;
    private final RaceService raceService;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Race>>> findAll(){
        return ResponseEntity.ok(raceService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<Race>> findById(@PathVariable Long id){
        return controllerFunctions.responseWithStatus(id, raceService::findById);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Race education, HttpServletRequest request){
        return controllerFunctions.statusCode(education, raceService::save, request);
    }
}
