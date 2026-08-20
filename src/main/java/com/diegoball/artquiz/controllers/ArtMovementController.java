package com.diegoball.artquiz.controllers;

import com.diegoball.artquiz.dto.artMovement.ArtMovementCreateDTO;
import com.diegoball.artquiz.entities.ArtMovement;
import com.diegoball.artquiz.services.ArtMovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/art-movements")
@RequiredArgsConstructor
public class ArtMovementController {

    private final ArtMovementService artMovementService;

    @GetMapping
    public List<ArtMovement> findAll() {
        return artMovementService.findAll();
    }

    @GetMapping("/{id}")
    public ArtMovement findById(@PathVariable Long id) {
        return artMovementService.findById(id);
    }

    @PostMapping
    public ArtMovement create(@Valid @RequestBody ArtMovementCreateDTO dto) {
        return artMovementService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        artMovementService.deleteById(id);
    }
}