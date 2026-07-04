package com.example.demo.models.dto.apiResponse.specify;

import com.example.demo.models.dto.absolute.EventDTO;
import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

public class EventApiResponse extends ApiSuccessResponse<EventDTO> {
    public EventApiResponse(
            String message,
            EventDTO data
    ){
        super("Successful" ,message, data);
    }
}
