package com.diegoball.artquiz.services;


import com.diegoball.artquiz.entities.ArtWork;
import com.diegoball.artquiz.entities.DailyChallenge;
import com.diegoball.artquiz.enums.QuestionType;
import com.diegoball.artquiz.repositories.ArtWorkRepository;
import com.diegoball.artquiz.repositories.DailyChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class DailyChallengeService {

    private final DailyChallengeRepository dailyChallengeRepository;
    private final ArtWorkRepository artWorkRepository;


    public List<DailyChallenge> findAll() {
        return dailyChallengeRepository.findAll();
    }

    public DailyChallenge getTodayChallenge() {
        return dailyChallengeRepository.findByDate(LocalDate.now())
                .orElseGet(this::generateNewChallenge);
    }

    private DailyChallenge generateNewChallenge() {
        DailyChallenge newDailyChallenge = new DailyChallenge();
        List<ArtWork> allArtworks = artWorkRepository.findAll();
        QuestionType[] allTypes = QuestionType.values();
        QuestionType newQuestionType;
        ArtWork newArtWork;

        do {
            newArtWork = allArtworks.get(ThreadLocalRandom.current().nextInt(allArtworks.size()));
            newQuestionType = allTypes[ThreadLocalRandom.current().nextInt(allTypes.length)];
        } while (isRepeated(newArtWork, newQuestionType));
        //guardamos en BD
        newDailyChallenge.setDate(LocalDate.now());
        newDailyChallenge.setArtWork(newArtWork);
        newDailyChallenge.setQuestionType(newQuestionType);
        dailyChallengeRepository.save(newDailyChallenge);
        return newDailyChallenge;
    }

    private boolean isRepeated(ArtWork artwork, QuestionType questionType) {
        List<DailyChallenge> recentChallenges = dailyChallengeRepository.getAllByDateAfter(LocalDate.now().minusDays(3));
        return recentChallenges.stream()
                .anyMatch(challenge ->
                        challenge.getArtWork().getId().equals(artwork.getId())
                                && challenge.getQuestionType() == questionType
                );
    }
}
