package com.diegoball.artquiz.services;

import com.diegoball.artquiz.dto.challengeAttempt.ChallengeAttemptCreateDTO;
import com.diegoball.artquiz.entities.ChallengeAttempt;
import com.diegoball.artquiz.entities.DailyChallenge;
import com.diegoball.artquiz.entities.User;
import com.diegoball.artquiz.repositories.ChallengeAttemptRepository;
import com.diegoball.artquiz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ChallengeAttemptService {

    private final ChallengeAttemptRepository challengeAttemptRepository;
    private final UserRepository userRepository;
    private final DailyChallengeService dailyChallengeService;

    public List<ChallengeAttempt> findAllByUserId(UUID id) {
        return challengeAttemptRepository.findAllByUserId(id);
    }

    public ChallengeAttempt registerChallengeByUserId(ChallengeAttemptCreateDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User no encontrado"));
        DailyChallenge dailyChallenge = dailyChallengeService.getTodayChallenge();
        if (challengeAttemptRepository.existsByUserIdAndDailyChallengeId(dto.getUserId(), dailyChallenge.getId())) {
            throw new RuntimeException("Ya has respondido al reto de hoy");
        } else {
            return generateNewChallengeAttemt(user, dailyChallenge, dto);
        }
    }

    private ChallengeAttempt generateNewChallengeAttemt(User user, DailyChallenge dailyChallenge, ChallengeAttemptCreateDTO dto) {
        ChallengeAttempt newAttempt = new ChallengeAttempt();
        newAttempt.setDailyChallenge(dailyChallenge);
        newAttempt.setUser(user);
        newAttempt.setAnsweredAt(LocalDateTime.now());
        newAttempt.setUserAnswered(dto.getUserAnswer());
        newAttempt.setIsCorrect(validateDailyChallenge(dto.getUserAnswer(), dailyChallenge));
        //guardar en BD
        challengeAttemptRepository.save(newAttempt);
        return newAttempt;
    }

    private Boolean validateDailyChallenge(String userAnswer, DailyChallenge dailyChallenge) {
        return switch (dailyChallenge.getQuestionType()) {
            case DATE -> false;
            case AUTHOR -> false;
            case MUSEUM -> false;
            case ARTWORK -> false;
            case AUTHOR_BORN -> false;
            case AUTHOR_DEATH -> false;
            case MOVEMENT -> false;
            case MOVEMENT_BORN -> false;
            case MOVEMENT_CENTURY -> false;
            default -> false;
        };
    }
}
