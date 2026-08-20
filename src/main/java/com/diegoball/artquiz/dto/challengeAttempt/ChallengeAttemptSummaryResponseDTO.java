package com.diegoball.artquiz.dto.challengeAttempt;

import com.diegoball.artquiz.dto.artWork.ArtWorkResponseDTO;
import com.diegoball.artquiz.dto.dailyChallenge.DailyChallengeSummaryResponseDTO;

import java.time.LocalDateTime;

public record ChallengeAttemptSummaryResponseDTO(
        Long id,
        LocalDateTime answeredAt,
        String userName,
        DailyChallengeSummaryResponseDTO dailyChallenge,
        Boolean isCorrect
) {
}
