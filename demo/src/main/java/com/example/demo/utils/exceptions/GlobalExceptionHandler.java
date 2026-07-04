package com.example.demo.utils.exceptions;

import com.example.demo.models.dto.apiResponse.ApiFailureResponse;
import com.example.demo.models.rules.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiFailureResponse> handleApiException(ApiException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ApiFailureResponse(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiFailureResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(java.util.stream.Collectors.joining(", "));

        if (message.isEmpty()) {
            message = "Erro de validação";
        }

        return ResponseEntity
                .status(400)
                .body(new ApiFailureResponse("VALIDATION_ERROR", message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiFailureResponse> handleGenericException(Exception e) {
        log.error("Unhandled exception occurred: ", e);
        return ResponseEntity.status(500).body(new ApiFailureResponse("INTERNAL_ERROR", "Erro interno do servidor"));
    }
}