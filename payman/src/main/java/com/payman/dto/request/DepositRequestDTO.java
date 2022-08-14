package com.payman.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequestDTO {
    private BigDecimal balance;
}
