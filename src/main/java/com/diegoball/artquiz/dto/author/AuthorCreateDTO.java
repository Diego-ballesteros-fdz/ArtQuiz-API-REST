package com.diegoball.artquiz.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthorCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar 150 caracteres")
    private String name;

    private Integer birthYear;
    private Integer deathYear;
    private String nationality;
    private String biography;
}