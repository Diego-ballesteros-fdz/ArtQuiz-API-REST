package com.diegoball.artquiz.entities;

import com.diegoball.artquiz.enums.QuestionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "daily_challenges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_work_id")
    private ArtWork artWork;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

}
