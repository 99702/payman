package com.payman.dto.request;

import com.payman.enumeration.Province;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UpdateCustomerRequest {
    @NotBlank(message = "full name cannot be blank")
    @Size(min = 2, max = 32, message = "full name must be between 2 and 32 characters long")
    private String fullName;


    @Past(message="date of birth must be less than today")
    @DateTimeFormat( pattern="yyyy-MM-dd")
    private LocalDate dob;

    @NotNull(message = "Please provide valid province")
    private Province province;

    @NotBlank(message = "citizenship cannot be blank")
    private String citizenshipNo;

    @NotBlank(message = "password cannot be blank")
    private String passwordCheck;
}
