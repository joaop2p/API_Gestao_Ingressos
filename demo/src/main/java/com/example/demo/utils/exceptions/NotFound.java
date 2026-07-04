package com.example.demo.utils.exceptions;

import com.example.demo.models.rules.ApiException;
import org.springframework.http.HttpStatus;

public class NotFound extends ApiException {
    public NotFound(String message) {
        super(message, HttpStatus.NOT_FOUND, "NOT_FOUND");
    }
}
