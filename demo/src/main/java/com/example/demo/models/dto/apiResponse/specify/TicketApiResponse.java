package com.example.demo.models.dto.apiResponse.specify;

import com.example.demo.models.dto.absolute.TicketDTO;
import com.example.demo.models.dto.absolute.UserDTO;
import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;

public class TicketApiResponse extends ApiSuccessResponse<TicketDTO> {
    public TicketApiResponse(
            String message,
            TicketDTO data
    ) {
        super("Successful", message, data);
    }
}
