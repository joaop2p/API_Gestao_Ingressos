package com.example.demo.controllers;

import com.example.demo.models.dto.absolute.TicketsListResumeDTO;
import com.example.demo.models.dto.apiResponse.ApiFailureResponse;
import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;
import com.example.demo.models.dto.apiResponse.specify.TicketGeralResumeApiResponse;
import com.example.demo.models.dto.apiResponse.specify.TicketResumeApiResponse;
import com.example.demo.models.dto.apiResponse.specify.TicketsListResumeApiResponse;
import com.example.demo.models.dto.forms.TicketFormDTO;
import com.example.demo.models.dto.resume.TicketGeralResumeDTO;
import com.example.demo.models.dto.resume.TicketResumeDTO;
import com.example.demo.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Tickets", description = "Ticket Management Endpoints")
@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    final TicketService ticketService;

    @PostMapping("/buy")
    @Operation(summary = "Buy a ticket", description = "Endpoint to purchase a ticket")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ticket purchased successfully", content = @Content(schema = @Schema(implementation = TicketResumeApiResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Event or User not found", content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Not enough tickets available", content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ApiFailureResponse.class)))
            }
    )
    public ResponseEntity<ApiSuccessResponse<TicketResumeDTO>> buyTicket(@RequestBody @Valid TicketFormDTO ticketFormDTO) {
        TicketResumeDTO newTicket = ticketService.create(ticketFormDTO);
        return ResponseEntity.ok(new ApiSuccessResponse<>("200", "Ticket purchased successfully", newTicket));
    }

    @GetMapping
    @Operation(summary = "Get ticket by user ID and event ID", description = "Retrieves a ticket based on the provided user ID and event ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ticket retrieved successfully", content = @Content(schema = @Schema(implementation = TicketsListResumeApiResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User or Event not found", content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
                    @ApiResponse(responseCode = "400", description = "User ID or Event ID must be provided", content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ApiFailureResponse.class)))
            })
    public ResponseEntity<ApiSuccessResponse<TicketsListResumeDTO>> getTicketById(@RequestParam(required = false) Long userId, @RequestParam(required = false) Long eventId) {
        TicketsListResumeDTO ticket = ticketService.getTicketsByUserId(userId, eventId);
        return ResponseEntity.ok(new ApiSuccessResponse<>("200", "Ticket retrieved successfully", ticket));
    }

    @GetMapping("/summary")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ticket summary retrieved successfully", content = @Content(schema = @Schema(implementation = TicketGeralResumeApiResponse.class))),
                    @ApiResponse(responseCode = "404", description = "No tickets found for the provided criteria", content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ApiFailureResponse.class)))
            }
    )
    @Operation(summary = "Get ticket summary", description = "Retrieves a summary of tickets based on the provided user ID and event ID")
    public ResponseEntity<ApiSuccessResponse<TicketGeralResumeDTO>> getTicketSummary() {
        TicketGeralResumeDTO ticketSummary = ticketService.geralSummary();
        return ResponseEntity.ok(new ApiSuccessResponse<>("200", "Ticket summary retrieved successfully", ticketSummary));
    }
}
