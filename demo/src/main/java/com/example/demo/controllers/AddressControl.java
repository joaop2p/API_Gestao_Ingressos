package com.example.demo.controllers;

import com.example.demo.models.dto.AddressFormDTO;
import com.example.demo.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@Tag(name="Address")
public class AddressControl {
    private final AddressService addressService;

    @PostMapping
    @Operation(summary = "Create a new address")
    public ResponseEntity<?> newAddress(@RequestBody @Valid AddressFormDTO addressFormDTO){
        try{
            return ResponseEntity.status(201).body(addressService.createNewAddress(addressFormDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
