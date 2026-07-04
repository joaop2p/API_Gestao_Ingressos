package com.example.demo.models.dto.forms;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para o formulário de endereço")
public record AddressFormDTO(
        @Schema(description = "Rua do endereço", example = "Rua Exemplo")
        @NotBlank
        String street,
        @Schema(description = "Número do endereço", example = "123")
        String number,
        @Schema(description = "Cidade do endereço", example = "Cidade Exemplo")
        @NotBlank
        String city,
        @Schema(description = "Estado do endereço", example = "Estado Exemplo")
        @NotBlank
        String state,
        @Schema(description = "CEP do endereço", example = "12345-678")
        @NotBlank
        String zipCode,
        @Schema(description = "Bairro do endereço", example = "Bairro Exemplo")
        @NotBlank
        String district
) {
}
