package com.example.demo.controllers;

import com.example.demo.models.dto.absolute.LocalDTO;
import com.example.demo.models.dto.apiResponse.ApiFailureResponse;
import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;
import com.example.demo.models.dto.apiResponse.specify.LocalApiResponse;
import com.example.demo.models.dto.forms.LocalFormDTO;
import com.example.demo.services.LocalService;
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
@RequestMapping("/locations")
@RequiredArgsConstructor
@Tag(name = "Locations", description = "Endpoints for location management")
public class LocalControl {
    final LocalService localService;

    @PostMapping
    @Operation(summary = "Create a new location", description = "Creates a new location and returns the persisted location entity in the response body.")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201", description = "Location created successfully",
                            content = @Content(schema = @Schema(implementation = LocalApiResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "409", description = "Location already exists",
                            content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Validation error",
                            content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal server error",
                            content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
                    )
            }
    )
    public ResponseEntity<ApiSuccessResponse<LocalDTO>> createLocation(@RequestBody @Valid LocalFormDTO localDTO) {
        LocalDTO newLocal = localService.createLocal(localDTO).toDTO();
        return ResponseEntity.ok(new ApiSuccessResponse<>("201", "Location created successfully", newLocal));
    }
}
