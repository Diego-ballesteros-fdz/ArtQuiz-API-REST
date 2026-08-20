package com.diegoball.artquiz.dto.dailyChallenge;

import com.diegoball.artquiz.enums.QuestionType;

import java.time.LocalDate;

public record DailyChallengeSummaryResponseDTO(
        Long id,
        LocalDate date,
        QuestionType questionType,
        String artWorkTitle
) {
}
