package org.example.gestor_de_ventas_api.service;

import org.example.gestor_de_ventas_api.dto.user.CreateUserRequest;
import org.example.gestor_de_ventas_api.dto.user.UpdateUserRequest;
import org.example.gestor_de_ventas_api.dto.user.UserResponse;
import org.example.gestor_de_ventas_api.entity.RoleUser;
import org.example.gestor_de_ventas_api.entity.StateUser;
import org.example.gestor_de_ventas_api.entity.User;
import org.example.gestor_de_ventas_api.exception.custom.ResourceAlreadyExistsException;
import org.example.gestor_de_ventas_api.exception.custom.ResourcesNotFoundException;
import org.example.gestor_de_ventas_api.mapper.UserMapper;
import org.example.gestor_de_ventas_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourcesNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponse save(CreateUserRequest user) {
            if (userRepository.findByUserName((user.getUserName())).isPresent()){
                throw new ResourceAlreadyExistsException("Username Already Exists");
            }
            User newUser = new User();
            newUser.setUserName(user.getUserName());
            newUser.setRoleUser(RoleUser.EMPLOYEE);
            newUser.setStateUser(StateUser.DEACTIVATED);
            return userMapper.toResponse(userRepository.save(newUser));
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }

    @Transactional
    public UserResponse update(Long id, UpdateUserRequest user) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("User not found"));

        Optional<User> otherUser = userRepository.findByUserName(user.getUserName());

        if (otherUser.isPresent() && !otherUser.get().getIdUser().equals(existingUser.getIdUser())) {
            throw new ResourceAlreadyExistsException("Username already in use");
        }

        existingUser.setUserName(user.getUserName());
        existingUser.setRoleUser(user.getRoleUser());
        existingUser.setStateUser(user.getStateUser());

        return userMapper.toResponse(existingUser);
    }

    public void deleteById(Long id) {
        User  user = userRepository.findById(id).orElseThrow(()->new ResourcesNotFoundException("User not found"));
        userRepository.deleteById(id);
    }

}
