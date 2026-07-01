package com.example.demo.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.Description;

import java.time.LocalDateTime;

@Schema(description = "DTO para o formulário de eventos")
public record EventDTO(
    @Schema(description = "Nome do evento", example = "Reunião de equipe")
    String name,
    @Schema(description = "Descrição do evento", example = "Reunião para discutir o andamento do projeto")
    String description,
    @Schema(description = "Data e hora de início do evento", example = "2024-06-01T10:00:00")
    LocalDateTime start,
    @Schema(description = "Data e hora de término do evento", example = "2024-06-01T12:00:00")
    LocalDateTime end,
    @Schema(description = "Local do evento", example = "Sala de conferências 1")
    String location
) {}
