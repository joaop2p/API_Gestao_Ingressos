package com.example.demo.controllers;

import com.example.demo.models.dto.apiResponse.ApiFailureResponse;
import com.example.demo.models.dto.apiResponse.specify.UserApiResponse;
import com.example.demo.models.dto.forms.UserFormDTO;
import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;
import com.example.demo.models.dto.absolute.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Endpoints for user management")
public class UsersControl {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user and returns a standardized success response with the creation result.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User created successfully",
                    content = @Content(schema = @Schema(implementation = UserApiResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Invalid input or user already exists",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
            )
    })
    public ResponseEntity<ApiSuccessResponse<String>> newUser(@RequestBody @Valid UserFormDTO user){
        String msg = "User created successfully, id: " + userService.createUser(user);
        return ResponseEntity.ok(new ApiSuccessResponse<>("SUCCESS", msg, null));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user by ID", description = "Updates the user identified by the provided ID and returns the updated form payload.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User updated successfully",
                    content = @Content(schema = @Schema(implementation = UserApiResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
            )
    })
    public ResponseEntity<UserFormDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserFormDTO user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID", description = "Returns the user data for the provided identifier.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User retrieved successfully",
                    content = @Content(schema = @Schema(implementation = UserApiResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
            )
    })
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id).toDTO());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID", description = "Deletes the user identified by the provided ID and returns a confirmation message.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "User deleted successfully",
                    content = @Content(schema = @Schema(implementation = ApiSuccessResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500", description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ApiFailureResponse.class))
            )
    })
    public ResponseEntity<ApiSuccessResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiSuccessResponse<>("SUCCESS", "User deleted successfully.", null));
    }
}
