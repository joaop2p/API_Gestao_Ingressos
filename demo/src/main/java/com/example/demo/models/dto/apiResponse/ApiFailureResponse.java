package com.example.demo.models.dto.apiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiFailureResponse implements ApiResponse {
    @Schema(description = "Response code", example = "FAILED")
    private final String code;

    @Schema(description = "Response message", example = "Operation failed")
    private final String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Timestamp of response", example = "2026-07-02T14:30:00")
    private final LocalDateTime timestamp;

    public ApiFailureResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}
