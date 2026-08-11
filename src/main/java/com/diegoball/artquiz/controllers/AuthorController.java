package com.diegoball.artquiz.controllers;

import com.diegoball.artquiz.dto.author.AuthorCreateDTO;
import com.diegoball.artquiz.dto.author.AuthorUpdateDTO;
import com.diegoball.artquiz.entities.Author;
import com.diegoball.artquiz.services.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping
    public Author create(@Valid @RequestBody AuthorCreateDTO dto) {
        return authorService.save(dto);
    }

    @PatchMapping("/{id}")
    public Author partialUpdate(@Valid @RequestBody AuthorUpdateDTO dto, @PathVariable Long id) {
        return authorService.partialUpdate(id, dto);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }
}