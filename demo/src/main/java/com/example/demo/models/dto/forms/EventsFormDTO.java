package com.example.demo.models.dto.forms;

import com.example.demo.models.entities.Local;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(description = "DTO para o formulário de eventos")
public record EventsFormDTO(
    @Schema(description = "Nome do evento", example = "Reunião de equipe")
    @NotBlank
    String name,
    @Schema(description = "Descrição do evento", example = "Reunião para discutir o andamento do projeto")
    String description,
    @Schema(description = "Data e hora de início do evento", example = "2024-06-01T10:00:00")
    @NotNull
    LocalDateTime start,
    @Schema(description = "Data e hora de término do evento", example = "2024-06-01T12:00:00")
    @NotNull
    LocalDateTime end,
    @Schema(description = "ID do local do evento", example = "1")
    @NotNull
    Long localId,
    @Schema(description = "Preço do evento", example = "100.0")
    double price
) {
}
