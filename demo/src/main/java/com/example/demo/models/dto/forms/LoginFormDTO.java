package com.example.demo.models.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para o formulário de login")
public record LoginFormDTO(
        @NotBlank(message = "Email não pode ser vazio")
        @Schema(description = "Email do usuário", example = "usuario@example.com")
        String email,
        @NotBlank(message = "Senha não pode ser vazia")
        @Schema(description = "Senha do usuário", example = "senha123")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String password
) {

}
