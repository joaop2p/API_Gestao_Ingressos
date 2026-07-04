package com.example.demo.models.dto.resume;

import com.example.demo.models.dto.absolute.EventDTO;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para o resumo geral de tickets")
public record TicketGeralResumeDTO(
        @Schema(description = "Total de tickets vendidos", example = "100")
        int totalSold,
        @Schema(description = "Receita total gerada pelos tickets", example = "5000.0")
        double revenue,
        @Schema(description = "Média de tickets vendidos por evento", example = "50.0")
        double mean,
        @Schema(description = "Evento com maior número de tickets vendidos")
        EventDTO biggestEvent
) {
}
