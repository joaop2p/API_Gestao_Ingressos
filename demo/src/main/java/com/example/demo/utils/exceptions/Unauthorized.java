package com.example.demo.utils.exceptions;

import com.example.demo.models.rules.ApiException;
import org.springframework.http.HttpStatus;

public class Unauthorized extends ApiException {
    public Unauthorized(String message) {
        super(message, HttpStatus.UNAUTHORIZED, "LOGIN_FAILED");
    }
}
