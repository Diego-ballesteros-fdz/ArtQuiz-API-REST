package com.diegoball.artquiz.dto.challengeAttempt;

import com.diegoball.artquiz.dto.dailyChallenge.DailyChallengeResponseDTO;
import com.diegoball.artquiz.dto.user.UserResponseDTO;

public record ChallengeAttemptResponseDTO(
        Long id,
        UserResponseDTO user,
        DailyChallengeResponseDTO dailyChallenge,
        String userAnswered,
        Boolean isCorrect

) {
}
