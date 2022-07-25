package com.payman.service.impl;

import com.payman.entity.Account;
import com.payman.entity.Deposit;
import com.payman.repository.DepositRepository;
import com.payman.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class DepositServiceImpl implements DepositService {
    @Autowired
    DepositRepository depositRepository;

    @Override
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
            String language) {
        Deposit deposit = new Deposit();
        deposit.setFrom(from);
        deposit.setTo(to);
        deposit.setAmount(amount);
        deposit.setIp(ip);
        deposit.setLanguage(language);
        deposit.setLatitude(latitude);
        deposit.setLongitude(longitude);
        deposit.setScreen(screen);
        deposit.setUserAgent(userAgent);
        deposit.setClientDateTime(clientDateTime);
        depositRepository.save(deposit);
    }
}
