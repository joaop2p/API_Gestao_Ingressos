package com.example.demo.models.dto.apiResponse.specify;

import com.example.demo.models.dto.absolute.TicketsListResumeDTO;
import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;
import com.example.demo.models.dto.resume.TicketResumeDTO;

public class TicketsListResumeApiResponse extends ApiSuccessResponse<TicketsListResumeDTO> {
    public TicketsListResumeApiResponse(
            String message,
            TicketsListResumeDTO data
    ){
        super("Successful", message, data);
    }
}
