package ru.aplk.dnd_service.authentication.routes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.aplk.dnd_service.users.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTOWithUser {
    private User user;
    private int status;
    private String jwt;
}
