package com.payman.controller;

import com.payman.dto.response.TransactionHistoryResponseDTO;
import com.payman.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    DepositService depositService;
    @GetMapping("history")
    private List<TransactionHistoryResponseDTO> getTransactionHistory(HttpServletRequest request){
       return depositService.getTransactionHistory(request) ;
    }

}
