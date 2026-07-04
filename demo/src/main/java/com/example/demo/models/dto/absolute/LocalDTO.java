package com.example.demo.models.dto.absolute;

import com.example.demo.models.others.LocalTypes;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "DTO para o formulário de local")
public record LocalDTO(
        @Schema(description = "ID do local", example = "1")
        Long id,
        @Schema(description = "Nome do local", example = "Local Exemplo")
        @NotBlank
        String name,
        @Schema(description = "Endereço do local")
        AddressDTO address,
        @Schema(description = "Capacidade do local", example = "100")
        @NotNull
        @Min(value = 1, message = "A capacidade deve ser maior que zero")
        @Positive
        int capacity,
        @Schema(description = "Tipo do local", example = "Estadio")
        LocalTypes type
) {
}
