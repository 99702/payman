package com.payman.service;

import com.payman.dto.response.TransactionHistoryResponseDTO;
import com.payman.entity.Account;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public interface DepositService {
    public void depositData(
            Account from,
            Account to,
            BigDecimal amount,
            String ip,
            double latitude,
            double longitude,
            String screen,
            String userAgent,
            String clientDateTime,
            String language
    );

    List<TransactionHistoryResponseDTO> getTransactionHistory(HttpServletRequest request);
}
