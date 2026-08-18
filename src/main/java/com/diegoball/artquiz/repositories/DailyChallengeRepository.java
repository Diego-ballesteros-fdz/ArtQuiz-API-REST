package com.diegoball.artquiz.repositories;


import com.diegoball.artquiz.entities.ArtWork;
import com.diegoball.artquiz.entities.DailyChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DailyChallengeRepository extends JpaRepository<DailyChallenge, Long> {
    Optional<DailyChallenge> findByDate(LocalDate date);
    List<DailyChallenge> getAllByDateAfter(LocalDate date);
}
