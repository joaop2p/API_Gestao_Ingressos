package com.example.demo.utils.exceptions;

import com.example.demo.models.rules.ApiException;
import org.springframework.http.HttpStatus;

public class NotAvailable extends ApiException {
    public NotAvailable(String message) {
        super(message, HttpStatus.CONFLICT,"NOT_AVAILABLE");
    }
}
