package com.example.demo.utils.exceptions;

import com.example.demo.models.rules.ApiException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExists extends ApiException {
    public UserAlreadyExists(String message) {
        super(message, HttpStatus.CONFLICT, "USER_ALREADY_EXISTS");
    }
}
