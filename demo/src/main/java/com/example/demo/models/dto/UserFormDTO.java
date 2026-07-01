package com.example.demo.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "")
public record UserFormDTO(
        @NotBlank
        String name,
        @NotBlank @Email
        String email,
        @NotBlank @Size(min = 8, message = "A senha deve conter no mínimo 10 caracteres")
        String password
) {

}
