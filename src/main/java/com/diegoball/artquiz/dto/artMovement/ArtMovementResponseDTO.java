package com.diegoball.artquiz.dto.artMovement;

public record ArtMovementResponseDTO(
        Long id,
        String name,
        Integer centuryStart,
        Integer centuryEnd,
        String location,
        String movementDescription
) {
}
