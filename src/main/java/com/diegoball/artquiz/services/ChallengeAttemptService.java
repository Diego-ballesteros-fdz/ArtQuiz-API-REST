package com.diegoball.artquiz.services;

import com.diegoball.artquiz.dto.challengeAttempt.ChallengeAttemptCreateDTO;
import com.diegoball.artquiz.entities.ChallengeAttempt;
import com.diegoball.artquiz.entities.DailyChallenge;
import com.diegoball.artquiz.entities.User;
import com.diegoball.artquiz.repositories.ChallengeAttemptRepository;
import com.diegoball.artquiz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
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
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User no encontrado"));
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
        boolean isCorrect = validateDailyChallenge(dto.getUserAnswer(), dailyChallenge);
        modifyUserStreak(user, isCorrect);
        newAttempt.setIsCorrect(isCorrect);
        //guardar en BD
        challengeAttemptRepository.save(newAttempt);
        return newAttempt;
    }

    private void modifyUserStreak(User user, boolean isCorrect) {
        if (isCorrect) {
            user.setCurrentStreak(user.getCurrentStreak() + 1);
            user.setMaxStreak(Math.max(user.getMaxStreak(), user.getCurrentStreak()));
            user.setTotalScore(user.getTotalScore() + 1);
        } else {
            user.setCurrentStreak(0);
        }
        userRepository.save(user);
    }

    private Boolean validateDailyChallenge(String userAnswer, DailyChallenge dailyChallenge) {
        return switch (dailyChallenge.getQuestionType()) {
            case DATE -> answersMatch(userAnswer, String.valueOf(dailyChallenge.getArtWork().getYear()));
            case AUTHOR -> answersMatch(userAnswer, dailyChallenge.getArtWork().getAuthor().getName());
            case MUSEUM -> answersMatch(userAnswer, dailyChallenge.getArtWork().getMuseum());
            case ARTWORK -> answersMatch(userAnswer, dailyChallenge.getArtWork().getTitle());
            case AUTHOR_BORN ->
                    answersMatch(userAnswer, String.valueOf(dailyChallenge.getArtWork().getAuthor().getBirthYear()));
            case AUTHOR_DEATH ->
                    answersMatch(userAnswer, String.valueOf(dailyChallenge.getArtWork().getAuthor().getDeathYear()));
            case MOVEMENT -> answersMatch(userAnswer, dailyChallenge.getArtWork().getArtMovement().getName());
            case MOVEMENT_BORN ->
                    answersMatch(userAnswer, String.valueOf(dailyChallenge.getArtWork().getArtMovement().getCenturyStart()));
            case MOVEMENT_CENTURY ->
                    answersMatch(userAnswer, (dailyChallenge.getArtWork().getArtMovement().getCenturyStart() + "-" + dailyChallenge.getArtWork().getArtMovement().getCenturyEnd()));
            default -> throw new IllegalStateException("QuestionType no soportado " + dailyChallenge.getQuestionType());
        };
    }

    private boolean answersMatch(String userAnswer, String correctAnswer) {
        String normalizedUser = normalize(removeDiacritics(userAnswer));
        String normalizedCorrect = normalize(removeDiacritics(correctAnswer));
        return normalizedUser.equals(normalizedCorrect);
    }

    private String normalize(String text) {
        return text.trim().toLowerCase().replaceAll("\\s+", " ");
    }

    private String removeDiacritics(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "");
    }
}
