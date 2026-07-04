package com.example.demo.models.dto.apiResponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "Successful response with generic data")
public class ApiSuccessResponse<T> implements ApiResponse {
    @Schema(description = "Response code", example = "SUCCESS")
    private final String code;

    @Schema(description = "Response message", example = "Operation completed successfully")
    private final String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(description = "Timestamp of response", example = "2026-07-02T14:30:00")
    private final LocalDateTime timestamp;

    @Schema(description = "Response data payload")
    private final T data;

    public ApiSuccessResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }
}