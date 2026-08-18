package com.diegoball.artquiz.controllers;


import com.diegoball.artquiz.entities.DailyChallenge;
import com.diegoball.artquiz.services.DailyChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dailyChallenges")
@RequiredArgsConstructor
public class DailyChallengeController {

    private final DailyChallengeService dailyChallengeService;

    @GetMapping
    public List<DailyChallenge> findAll() {
        return dailyChallengeService.findAll();
    }

    @GetMapping("/today")
    public DailyChallenge getTodayChallenge() {
        return dailyChallengeService.getTodayChallenge();
    }


}
