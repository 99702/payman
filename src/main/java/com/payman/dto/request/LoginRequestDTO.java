package com.payman.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDTO {

    @NotNull(message = "Mobile number cannot be null")
    @NotBlank(message = "Mobile number cannot be blank")
    private String mobile;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
