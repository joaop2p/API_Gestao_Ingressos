package com.example.demo.models.dto.apiResponse.specify;

import com.example.demo.models.dto.absolute.UserDTO;
import com.example.demo.models.dto.apiResponse.ApiSuccessResponse;

public class UserApiResponse extends ApiSuccessResponse<UserDTO> {
    public UserApiResponse(
            String message,
            UserDTO data
    ){
        super("Successful", message, data);
    }
}
