package com.diegoball.artquiz.dto.author;

public record AuthorResponseDTO(
        Long id,
        String name,
        Integer birthYear,
        Integer deathYear,
        String nationality,
        String biography
) {
}
