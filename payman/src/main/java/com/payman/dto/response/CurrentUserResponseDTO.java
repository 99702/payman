package com.payman.dto.response;

import com.payman.enumeration.Province;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CurrentUserResponseDTO {
    private String full_name;
    private LocalDate dob;
    private Province province;
    private String mobile;
    private String citizenshipNo;
    private LocalDateTime createdAt;
    private String type;
}