package com.example.demo.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para o formulário de endereço")
public record AddressFormDTO(
        @Schema(description = "Rua do endereço", example = "Rua Exemplo")
        String street,
        @Schema(description = "Número do endereço", example = "123")
        String number,
        @Schema(description = "Cidade do endereço", example = "Cidade Exemplo")
        String city,
        @Schema(description = "Estado do endereço", example = "Estado Exemplo")
        String state,
        @Schema(description = "CEP do endereço", example = "12345-678")
        String zipCode
) {
}
