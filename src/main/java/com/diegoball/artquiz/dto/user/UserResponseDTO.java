package com.diegoball.artquiz.dto.user;

import com.diegoball.artquiz.enums.Role;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String userName,
        String email,
        Role role,
        Integer currentStreak,
        Integer totalScore,
        Integer maxStreak
) {
}