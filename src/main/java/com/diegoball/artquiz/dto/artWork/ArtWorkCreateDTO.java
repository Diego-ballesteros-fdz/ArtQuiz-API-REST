package com.diegoball.artquiz.dto.artWork;

import com.diegoball.artquiz.entities.ArtMovement;
import com.diegoball.artquiz.entities.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ArtWorkCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar 150 caracteres")
    private String title;

    private Integer year;
    private String imageUrl;
    private String museum;

    @NotNull(message = "El id del autor es obligatoiro.")
    private Long authorId;

    @NotNull(message = "El id del movimiento artistico es obligatoiro.")
    private Long artMovementId;
}