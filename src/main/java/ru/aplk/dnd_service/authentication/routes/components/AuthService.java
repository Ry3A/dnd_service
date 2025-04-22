package ru.aplk.dnd_service.authentication.routes.components;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aplk.dnd_service.authentication.jwt.JwtService;
import ru.aplk.dnd_service.authentication.routes.dto.AuthRequestDTO;
import ru.aplk.dnd_service.authentication.routes.dto.AuthResponseDTO;
import ru.aplk.dnd_service.authentication.routes.dto.AuthResponseDTOWithUser;
import ru.aplk.dnd_service.authentication.routes.dto.RegisterRequestDTO;
import ru.aplk.dnd_service.users.Role;
import ru.aplk.dnd_service.users.User;
import ru.aplk.dnd_service.users.UserRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public User getUserByHttpRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return userRepo
                    .findByEmail(jwtService.getEmail(authHeader.substring(7)))
                    .orElse(null);
        }

        return null;
    }

    public boolean isNotAdmin(HttpServletRequest request) {
        User user = getUserByHttpRequest(request);
        return user == null || user.getRole() != Role.ADMIN;
    }

    public AuthResponseDTO register(RegisterRequestDTO requestData) {
        return register(requestData, Role.USER);
    }
    public AuthResponseDTO register(RegisterRequestDTO requestData, Role role) {
        Optional<User> dbUser = userRepo.findByEmail(requestData.getEmail());
        if (dbUser.isPresent()) {
            return AuthResponseDTO.builder().jwt(null).status(409).build();
        }

        User user = User.builder()
                .email(requestData.getEmail())
                .password(passwordEncoder.encode(requestData.getPassword()))
                .role(role)
                .build();

        userRepo.save(user);

        return AuthResponseDTO.builder()
                .jwt(jwtService.createToken(user))
                .status(200)
                .build();
    }

    public AuthResponseDTOWithUser login(AuthRequestDTO authData) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authData.getEmail(),
                        authData.getPassword()
                )
        );

        User user = userRepo.findByEmail(authData.getEmail())
                .orElseThrow();

        return AuthResponseDTOWithUser.builder()
                .jwt(jwtService.createToken(user))
                .user(user)
                .status(200)
                .build();
    }
}