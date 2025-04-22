package ru.aplk.dnd_service.users;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aplk.dnd_service.additions.types.ResponseWithStatus;
import ru.aplk.dnd_service.authentication.routes.components.AuthService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

/*
    public UserDTO findById(Long id, HttpServletRequest request) {

    }
    public UserDTO findByEmail(String email, HttpServletRequest request) {

    }
    */
    public ResponseWithStatus<List<User>> findAll(HttpServletRequest request) {

        if(authService.isNotAdmin(request)) {
            return ResponseWithStatus.empty(403);
        }


        return ResponseWithStatus.create(200, userRepo.findAll());
    }
    public List<User> findUser(){
        return userRepo.findAll();
    }

    public List<User> findAllWeb(){
        return userRepo.findAll();
    }

    /*
    public Void save(User user, HttpServletRequest request) {

    }
    public Void change(User user, HttpServletRequest request) {

    }
    public Void deleteById(HttpServletRequest request) {
    }
    */

}
