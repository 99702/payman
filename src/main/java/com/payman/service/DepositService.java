package com.payman.service;

import com.payman.entity.Account;

import java.math.BigDecimal;

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
}
