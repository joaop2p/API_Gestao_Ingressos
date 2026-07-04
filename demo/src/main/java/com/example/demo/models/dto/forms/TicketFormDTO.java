package com.example.demo.models.dto.forms;

import com.example.demo.models.entities.Event;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para o formulário de tickets")
public record TicketFormDTO(
        @Schema(description = "ID do evento", example = "1")
        Long eventId,
        @Schema(description = "ID do usuário", example = "1")
        Long userId,
        @Schema(description = "Quantidade de tickets", example = "2")
        int quantity
) {
}
