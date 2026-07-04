package com.example.demo.utils.exceptions;

import com.example.demo.models.rules.ApiException;
import org.springframework.http.HttpStatus;

public class ValueError extends ApiException {
    public ValueError(String message) {
        super(message, HttpStatus.BAD_REQUEST, "VALUE_ERROR");
    }
}
