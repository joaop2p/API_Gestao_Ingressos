package com.example.demo.utils.exceptions;

import com.example.demo.models.rules.ApiException;
import org.springframework.http.HttpStatus;

public class Conflict extends ApiException {
    public Conflict(String message) {
        super(message, HttpStatus.CONFLICT, "EVENT_CONFLICT");
    }
}
