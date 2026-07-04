package com.example.demo.models.dto.apiResponse.specify;

import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;
import com.example.demo.models.dto.resume.TicketGeralResumeDTO;

public class TicketGeralResumeApiResponse extends ApiSuccessResponse<TicketGeralResumeDTO> {

    public TicketGeralResumeApiResponse(
            String message,
            TicketGeralResumeDTO data
    ){
        super("Successful" ,message, data);
    }
}
