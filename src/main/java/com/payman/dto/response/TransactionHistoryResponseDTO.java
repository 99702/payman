package com.payman.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionHistoryResponseDTO {
    private BigDecimal amount;
    private String from;
    private String to;
//    private String ago;
    private String clientDateTime;
}
