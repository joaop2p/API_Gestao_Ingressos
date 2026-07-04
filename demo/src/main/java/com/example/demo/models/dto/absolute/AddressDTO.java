package com.example.demo.models.dto.absolute;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para o formulário de endereço")
public record AddressDTO(
        @Schema(description = "ID do endereço", example = "1")
        Long id,
        @Schema(description = "Rua do endereço", example = "Rua Exemplo")
        String street,
        @Schema(description = "Número do endereço", example = "123")
        String number,
        @Schema(description = "Cidade do endereço", example = "Cidade Exemplo")
        String city,
        @Schema(description = "Estado do endereço", example = "Estado Exemplo")
        String state,
        @Schema(description = "CEP do endereço", example = "12345-678")
        String zipCode,
        @Schema(description = "Bairro do endereço", example = "Bairro Exemplo")
        String district
) {
}
