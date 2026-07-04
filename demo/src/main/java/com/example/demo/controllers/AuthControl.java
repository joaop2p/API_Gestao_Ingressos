package com.example.demo.controllers;

import com.example.demo.models.dto.absolute.TokenDTO;
import com.example.demo.models.dto.apiResponse.ApiFailureResponse;
import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;
import com.example.demo.models.dto.forms.LoginFormDTO;
import com.example.demo.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "Endpoints relacionados à autenticação")
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthControl {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and generate token", description = "Authenticates the user with provided credentials and returns a token if successful.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(schema = @Schema(implementation = ApiSuccessResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials", content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request - Validation errors", content = @Content(schema = @Schema(implementation = ApiFailureResponse.class)))
    })
    public ResponseEntity<ApiSuccessResponse<TokenDTO>> login(@RequestBody @Valid LoginFormDTO loginRequest) {
        TokenDTO token = authService.authenticate(loginRequest);
        return ResponseEntity.ok(new ApiSuccessResponse<>("SUCCESS", "Login successful", token));
    }
}
