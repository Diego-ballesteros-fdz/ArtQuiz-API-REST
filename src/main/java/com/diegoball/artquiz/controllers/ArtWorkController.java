package com.diegoball.artquiz.controllers;


import com.diegoball.artquiz.dto.artWork.ArtWorkCreateDTO;
import com.diegoball.artquiz.entities.ArtMovement;
import com.diegoball.artquiz.entities.ArtWork;
import com.diegoball.artquiz.entities.Author;
import com.diegoball.artquiz.repositories.ArtMovementRepository;
import com.diegoball.artquiz.repositories.AuthorRepository;
import com.diegoball.artquiz.services.ArtWorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artwork")
@RequiredArgsConstructor
public class ArtWorkController {

    private final ArtWorkService artWorkService;


    @GetMapping
    public List<ArtWork> findAll() {
        return artWorkService.findAll();
    }

    @GetMapping("/{id}")
    public ArtWork findById(@PathVariable Long id) {
        return artWorkService.findById(id);
    }

    @PostMapping
    public ArtWork create(@Valid @RequestBody ArtWorkCreateDTO dto) {
        return artWorkService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        artWorkService.deleteById(id);
    }
}