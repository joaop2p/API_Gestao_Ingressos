package com.example.demo.controllers;

import com.example.demo.models.dto.apiResponse.ApiFailureResponse;
import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;
import com.example.demo.models.dto.apiResponse.specify.EventApiResponse;
import com.example.demo.models.dto.forms.EventsFormDTO;
import com.example.demo.models.dto.absolute.EventDTO;
import com.example.demo.services.EventsService;
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

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Event Management Endpoints")
public class EventsController {
    final EventsService eventsService;

    @PostMapping
    @Operation(summary = "Create a new event", description = "Creates a new event and returns the created resource wrapped in the standardized event response.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully",
                    content = @Content(schema = @Schema(implementation = EventApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error or value error",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
            @ApiResponse(responseCode = "404", description = "Location not found",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
            @ApiResponse(responseCode = "409", description = "Event conflict detected",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class)))
    })
    public ResponseEntity<ApiSuccessResponse<EventDTO>> createEvent(@RequestBody @Valid EventsFormDTO eventsFormDTO) {
        EventDTO event = eventsService.createEvent(eventsFormDTO).toDTO();
        return ResponseEntity.ok(new ApiSuccessResponse<>("CREATED", "Event created successfully", event));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an event by ID", description = "Returns the event identified by the provided ID wrapped in the standardized event response.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found successfully",
                    content = @Content(schema = @Schema(implementation = EventApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "Event not found",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class)))
    })
    public ResponseEntity<ApiSuccessResponse<EventDTO>> getEventById(@PathVariable Long id) {
        EventDTO event = eventsService.getEventById(id).toDTO();
        return ResponseEntity.ok(new ApiSuccessResponse<>("SUCCESS", "Event retrieved successfully", event));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing event by ID", description = "Updates the event identified by the provided ID and returns the updated resource wrapped in the standardized event response.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event updated successfully",
                    content = @Content(schema = @Schema(implementation = EventApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or value error",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
            @ApiResponse(responseCode = "404", description = "Event not found",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
            @ApiResponse(responseCode = "409", description = "Event conflict detected",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class)))
    })
    public ResponseEntity<ApiSuccessResponse<EventDTO>> updateEvent(
            @PathVariable Long id,
            @RequestBody @Valid EventsFormDTO eventsFormDTO
    ) {
        EventDTO updatedEvent = eventsService.update(eventsFormDTO, id);
        return ResponseEntity.ok(new ApiSuccessResponse<>("UPDATED", "Event updated successfully", updatedEvent));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an event by ID", description = "Deletes the event identified by the provided ID. No response body is returned on success.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event deleted successfully", content = @Content(schema = @Schema(implementation = ApiSuccessResponse.class))),
            @ApiResponse(responseCode = "404", description = "Event not found",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class)))
    })
    public ResponseEntity<ApiSuccessResponse<Void>> deleteEvent(@PathVariable Long id) {
        eventsService.deleteEvent(id);
        return ResponseEntity.ok(new ApiSuccessResponse<>("DELETED", "Event deleted successfully", null));
    }
}