package com.example.demo.models.dto.apiResponse.specify;

import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;
import com.example.demo.models.dto.resume.TicketResumeDTO;

public class TicketResumeApiResponse extends ApiSuccessResponse<TicketResumeDTO> {
    public TicketResumeApiResponse(
            String message,
            TicketResumeDTO data
    ){
        super("Successful", message, data);
    }
}
