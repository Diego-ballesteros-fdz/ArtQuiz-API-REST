package com.diegoball.artquiz.repositories;


import com.diegoball.artquiz.entities.ChallengeAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChallengeAttemptRepository extends JpaRepository<ChallengeAttempt, Long> {
    List<ChallengeAttempt> findAllByUserId(UUID userId);
    boolean existsByUserIdAndDailyChallengeId(UUID userId, Long dailyChallengeId);
}
