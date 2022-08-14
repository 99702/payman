package com.payman.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterAAccountDTOResponse {
    private Boolean success;
    private String fullName;
    private String mobile;
    private String account_number;
    private BigDecimal balance;
}