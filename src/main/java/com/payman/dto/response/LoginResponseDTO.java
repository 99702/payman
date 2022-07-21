package com.payman.dto.response;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private String message;
    private String fullName;
}
