package org.example.gestor_de_ventas_api.mapper;

import org.example.gestor_de_ventas_api.dto.user.UserResponse;
import org.example.gestor_de_ventas_api.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getIdUser(),
                user.getUserName(),
                user.getRoleUser().name(),
                user.getStateUser().name()
        );
    }

}
