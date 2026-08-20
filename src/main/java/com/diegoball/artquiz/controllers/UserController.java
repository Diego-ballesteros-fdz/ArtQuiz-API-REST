package com.diegoball.artquiz.controllers;


import com.diegoball.artquiz.dto.user.UserCreateDTO;
import com.diegoball.artquiz.dto.user.UserUpdateDTO;
import com.diegoball.artquiz.entities.User;
import com.diegoball.artquiz.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PostMapping
    public User create(@Valid @RequestBody UserCreateDTO dto) {
        return userService.save(dto);
    }

    @PatchMapping("/{id}")
    public User partialUpdate(@Valid @RequestBody UserUpdateDTO dto, @PathVariable UUID id) {
        return userService.partialUpdate(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        userService.deleteById(id);
    }
}
