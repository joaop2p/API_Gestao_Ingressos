package com.example.demo.models.dto.absolute;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "DTO para o formulário de ticket")
public record TicketDTO(
        @Schema(description = "ID do ticket")
        Long id,
        @Schema(description = "Data de compra do ticket", example = "2023-01-01T00:00:00Z")
        Instant purchaseDate
) {
}
