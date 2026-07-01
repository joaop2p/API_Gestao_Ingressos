package com.example.demo.controllers;

import com.example.demo.models.dto.UserFormDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name="Users")
public class UsersControl {
    private final UserService userService;

    @GetMapping
    public String show(){
        return "Is Working";
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<?> newUser(@RequestBody @Valid UserFormDTO user){
        return ResponseEntity.status(201).body(userService.createUser(user));
    }
}
