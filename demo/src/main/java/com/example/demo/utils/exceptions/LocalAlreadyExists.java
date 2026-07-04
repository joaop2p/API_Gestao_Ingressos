package com.example.demo.utils.exceptions;

import com.example.demo.models.rules.ApiException;
import org.springframework.http.HttpStatus;

public class LocalAlreadyExists extends ApiException {
    public LocalAlreadyExists(String message) {
        super(message, HttpStatus.CONFLICT, "LOCAL_ALREADY_EXISTS");
    }
}
