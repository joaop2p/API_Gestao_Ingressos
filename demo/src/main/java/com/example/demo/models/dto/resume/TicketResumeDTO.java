package com.example.demo.models.dto.resume;

import com.example.demo.models.dto.absolute.EventDTO;
import com.example.demo.models.dto.absolute.TicketDTO;
import com.example.demo.models.dto.absolute.UserDTO;
import com.example.demo.models.entities.Ticket;
import com.example.demo.models.entities.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO para o resumo de ticket")
public record TicketResumeDTO(
        @Schema(description = "Informações do usuário")
        UserDTO user,
        @Schema(description = "Informações do evento")
        EventDTO event,
        @Schema(description = "Lista de tickets")
        List<TicketDTO> tickets,
        @Schema(description = "Total pago pelos tickets", example = "100.0")
        double totalPaid
)  {

}
