package com.payman.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class GetACustomerDetails {
    private BigDecimal balance;
    private String full_name;
    private String mobile;
    private String gender;
    private LocalDate dob;
    private String citizenship_number;
}
