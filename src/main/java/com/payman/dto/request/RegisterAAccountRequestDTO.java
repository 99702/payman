package com.payman.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterAAccountRequestDTO {


    @NotEmpty(message = "Mobile number cannot be empty")
    @NotBlank(message = "Mobile number cannot be blank")
    @NotNull(message = "Mobile number cannot be null")
    @Size(min = 10, max = 10, message = "Number should have at least 10 or less than 17 digits")
    private String mobile;
}