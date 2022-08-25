package com.payman.dto.response;

import com.payman.enumeration.Gender;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class GetAllCustomer {
    private String full_name;
    private String mobile;
    private LocalDate dob;
    private String citizenship_number;
    private BigDecimal balance;
    private String account_number;
    private Gender gender;
}