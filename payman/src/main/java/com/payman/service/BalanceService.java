package com.payman.service;

import com.payman.dto.request.BalanceCheckResponse;
import com.payman.dto.request.DepositRequestDTO;
import com.payman.dto.response.BalanceDepositResponse;
import com.payman.dto.response.BalanceWithdrawResponse;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public interface BalanceService {
    BalanceWithdrawResponse withdraw(HttpServletRequest request);
    BalanceDepositResponse deposit(HttpServletRequest request, DepositRequestDTO depositRequestDTO, String accountNumber, String mobileNumber);
    BalanceCheckResponse checkBalance(HttpServletRequest request);
}
