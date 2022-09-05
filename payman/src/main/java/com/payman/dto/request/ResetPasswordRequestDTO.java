package com.payman.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ResetPasswordRequestDTO {

    @NotBlank(message = "new password cannot be blank")
    @Size(min = 8, message = "Minimum 8 characters required")
    private String newPassword;
}
