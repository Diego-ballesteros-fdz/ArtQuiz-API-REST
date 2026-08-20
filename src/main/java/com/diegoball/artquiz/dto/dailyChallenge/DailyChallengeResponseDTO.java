package com.diegoball.artquiz.dto.dailyChallenge;

import com.diegoball.artquiz.dto.artWork.ArtWorkResponseDTO;
import com.diegoball.artquiz.enums.QuestionType;

import java.time.LocalDate;


public record DailyChallengeResponseDTO(
        Long id,
        LocalDate date,
        QuestionType questionType,
        ArtWorkResponseDTO artWork
) {
}
