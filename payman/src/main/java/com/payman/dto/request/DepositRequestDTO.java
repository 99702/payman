package com.payman.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class DepositRequestDTO {
    @NotNull
    private BigDecimal balance;
    @NotNull
    private String message;
}
