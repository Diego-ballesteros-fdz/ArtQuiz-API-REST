package com.diegoball.artquiz.controllers;


import com.diegoball.artquiz.dto.challengeAttempt.ChallengeAttemptCreateDTO;
import com.diegoball.artquiz.entities.ChallengeAttempt;
import com.diegoball.artquiz.services.ChallengeAttemptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/challenge-attempts")
@RequiredArgsConstructor
public class ChallengeAttemptController {

    private final ChallengeAttemptService challengeAttemptService;

    @GetMapping("/{id}")
    public List<ChallengeAttempt> findAllByUserId(@PathVariable UUID id) {
        return challengeAttemptService.findAllByUserId(id);
    }

    @PostMapping
    public ChallengeAttempt registerNewAttempt(@Valid @RequestBody ChallengeAttemptCreateDTO dto) {
        return challengeAttemptService.registerChallengeByUserId(dto);
    }


}
