package com.example.demo.models.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO para o formulário de usuário")
public record UserFormDTO(
        @NotBlank(message = "Nome não pode ser vazio")
        String name,
        @NotBlank(message = "Email não pode ser vazio") @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Senha não pode ser vazia") @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
        String password,
        @NotBlank(message = "CPF não pode ser vazio") @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 caracteres")
        String documentId,
        @Schema(description = "Endereço do usuário")
        @NotNull(message = "Endereço não pode ser nulo")
        AddressFormDTO address
) {
}
