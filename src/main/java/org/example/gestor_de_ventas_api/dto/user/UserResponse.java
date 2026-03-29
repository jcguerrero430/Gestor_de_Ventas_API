package org.example.gestor_de_ventas_api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long idUser;
    private String userName;
    private String roleUser;
    private String stateUser;

}
