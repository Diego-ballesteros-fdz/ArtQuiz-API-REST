package com.diegoball.artquiz.dto.user;


import com.diegoball.artquiz.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDTO {

    @Size(max = 150, message = "El nombre no puede superar 150 caracteres")
    private String userName;

    @Email(message = "El email debe ser válido")
    private String email;

    private String password;

//    private Role role;

}
