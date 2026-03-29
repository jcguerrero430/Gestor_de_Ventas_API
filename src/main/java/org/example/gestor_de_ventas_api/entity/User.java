package org.example.gestor_de_ventas_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(unique = true, nullable = false)
    private String userName;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser = RoleUser.EMPLOYEE;

    @Enumerated(EnumType.STRING)
    private StateUser stateUser = StateUser.DEACTIVATED;

}
