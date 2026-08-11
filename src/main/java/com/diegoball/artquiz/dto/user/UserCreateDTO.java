package com.diegoball.artquiz.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar 150 caracteres")
    private String userName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    //PENDIENTE DE SEGURIDAD
    @NotBlank(message = "El password es obligatorio")
    private String password;

}
