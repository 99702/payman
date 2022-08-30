package com.payman.controller;

import com.payman.dto.request.BalanceCheckResponse;
import com.payman.dto.request.DepositRequestDTO;
import com.payman.dto.response.BalanceDepositResponse;
import com.payman.dto.response.BalanceWithdrawResponse;
import com.payman.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/balance")
@CrossOrigin("*")
public class BalanceController {
    @Autowired
    BalanceService balanceService;

    @GetMapping("check")
    public BalanceCheckResponse checkBalance(HttpServletRequest request){
        return balanceService.checkBalance(request);
    }

    @PostMapping("deposit")
    public BalanceDepositResponse deposit(HttpServletRequest request, @RequestParam(value = "accountNumber", required = false) String accountNumber, @RequestParam(value = "mobileNumber", required = false) String mobileNumber, @RequestBody DepositRequestDTO depositRequestDTO){
        return balanceService.deposit(request, depositRequestDTO,  accountNumber, mobileNumber);
    }

    @PostMapping("withdraw")
    public BalanceWithdrawResponse withdraw(HttpServletRequest request) {
       return balanceService.withdraw(request);
    }
}