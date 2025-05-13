package com.example.usuariocrud.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistracionUsuarioDto {

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    // @Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
    @Size(min = 3, message = "El nombre de usuario debe tener al menos 3 caracteres")
    @Size(max = 20, message = "El nombre de usuario no puede tener más de 20 caracteres")
    private String usuario;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico no es válido")
    private String email;

}
