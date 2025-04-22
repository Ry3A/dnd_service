package ru.aplk.dnd_service.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aplk.dnd_service.additions.types.ResponseWithStatus;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<User>>> findAll(
            @NonNull HttpServletRequest request
    ) {
        ResponseWithStatus<List<User>> response = userService.findAll(request);

        return ResponseEntity.status(response.getStatus()).body(response);
    }
    /*
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(
            @PathVariable Long id,
            @NonNull HttpServletRequest request
    ) {
    }
    @GetMapping(params = "email")
    public ResponseEntity<UserDTO> findByEmail(
            @RequestParam String email,
            @NonNull HttpServletRequest request
    ) {
    }
    @PostMapping
    public ResponseEntity save(
            @RequestBody User user,
            @NonNull HttpServletRequest request
    ) {
    }
    @PutMapping
    public ResponseEntity change(
            @RequestBody User user,
            @NonNull HttpServletRequest request
    ) {
    }
    @DeleteMapping
    public ResponseEntity deleteById(@NonNull HttpServletRequest request) {
    }
    */

}
