package com.diegoball.artquiz.dto.artWork;

import com.diegoball.artquiz.dto.artMovement.ArtMovementResponseDTO;
import com.diegoball.artquiz.dto.author.AuthorResponseDTO;

public record ArtWorkResponseDTO(
        Long id,
        String title,
        Integer year,
        String imageUrl,
        String museum,
        AuthorResponseDTO author,
        ArtMovementResponseDTO artMovement
) {
}
