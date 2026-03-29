package org.example.gestor_de_ventas_api.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "The username is required")
    @Size(min = 3, max = 30, message = "The username must be between 3 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._]+$", message = "The username can only contain letters, numbers, dots and underscores")
    private String userName;

}
