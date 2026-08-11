package com.diegoball.artquiz.services;


import com.diegoball.artquiz.dto.user.UserCreateDTO;
import com.diegoball.artquiz.dto.user.UserUpdateDTO;
import com.diegoball.artquiz.entities.User;
import com.diegoball.artquiz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("user no encontrado con id: " + id));
    }

    public User save(UserCreateDTO dto) {
        User user = new User();

        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        // PENDIENTE DE SEGURIDAD: hashear con BCrypt en Fase 2
        user.setPassword(dto.getPassword());
        user.setCurrentStreak(0);
        user.setTotalScore(0);

        return userRepository.save(user);
    }

    public User partialUpdate(UUID id, UserUpdateDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user no encontrado con id: " + id));

        if (dto.getUserName() != null) {
            user.setUserName(dto.getUserName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
//        if (dto.getRole() != null) {
//            user.setRole(dto.getRole());
//        }

        return userRepository.save(user);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
