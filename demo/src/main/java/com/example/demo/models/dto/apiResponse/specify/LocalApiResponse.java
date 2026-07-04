package com.example.demo.models.dto.apiResponse.specify;

import com.example.demo.models.dto.absolute.LocalDTO;
import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;

public class LocalApiResponse extends ApiSuccessResponse<LocalDTO>{
    public LocalApiResponse(
            String message,
            LocalDTO data
    ) {
        super("Successful", message, data);
    }
}
