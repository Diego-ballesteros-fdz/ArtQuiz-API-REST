package com.diegoball.artquiz.dto.challengeAttempt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ChallengeAttemptCreateDTO {

    @NotNull(message = "El userId es obligatorio")
    private UUID userId;

    @NotBlank(message = "La respuesta es obligatoria")
    private String userAnswer;
}