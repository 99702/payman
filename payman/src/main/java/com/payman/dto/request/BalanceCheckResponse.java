package com.payman.dto.request;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class BalanceCheckResponse {
    BigDecimal balance;
}