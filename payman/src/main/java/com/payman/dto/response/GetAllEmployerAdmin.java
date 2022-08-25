package com.payman.dto.response;

import com.payman.enumeration.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class GetAllEmployerAdmin {
    private String fullName;
    private LocalDate dob;
    private Boolean enabled;
    private Gender gender;
    private String citizenshipNo;
    private LocalDateTime createdAt;
    private String mobile;
}