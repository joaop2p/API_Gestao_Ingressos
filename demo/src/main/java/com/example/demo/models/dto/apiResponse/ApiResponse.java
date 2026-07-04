package com.example.demo.models.dto.apiResponse;

public interface ApiResponse {
    String getCode();
    String getMessage();

    public static ApiResponse of(String code, String message) {
        return new ApiResponse() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }
}
