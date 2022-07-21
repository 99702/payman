package com.payman.dto.response;

import lombok.Data;

@Data
public class RegisterACustomerDTOResponse {
    private String status;
    private String message;
    private String fullName;
}