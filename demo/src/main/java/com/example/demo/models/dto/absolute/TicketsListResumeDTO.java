package com.example.demo.models.dto.absolute;

import com.example.demo.models.dto.resume.TicketResumeDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO para o resumo de tickets")
public record TicketsListResumeDTO(
        @Schema(description = "Lista de resumos de tickets")
        List<TicketResumeDTO> ticketResumes
) {
}
