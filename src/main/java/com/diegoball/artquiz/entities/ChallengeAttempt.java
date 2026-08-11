package com.diegoball.artquiz.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "challenge_attempts",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "daily_challenge_id"})
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChallengeAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "answered_at")
    private LocalDateTime answeredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_challenge_id")
    private DailyChallenge dailyChallenge;

    @Column(name = "user_answered")
    private String userAnswered;

    private Boolean isCorrect;
}
