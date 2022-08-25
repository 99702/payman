package com.payman.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceDepositResponse {
    private String from_account;
    private String to_account;
    private BigDecimal balance;
    private String success;
    private String message;

}
