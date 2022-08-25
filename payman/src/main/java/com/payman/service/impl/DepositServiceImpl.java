package com.payman.service.impl;

import com.payman.dto.response.TransactionHistoryResponseDTO;
import com.payman.entity.Account;
import com.payman.entity.Customer;
import com.payman.entity.Deposit;
import com.payman.repository.AccountRepository;
import com.payman.repository.CustomerRepository;
import com.payman.repository.DepositRepository;
import com.payman.service.DepositService;
import com.payman.utils.AES;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class DepositServiceImpl implements DepositService {

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Autowired
    DepositRepository depositRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AES aes;

    @Override
    public void depositData(Account from, Account to, BigDecimal amount, String ip, double latitude, double longitude, String screen, String userAgent, String clientDateTime,
        String language, String message) {
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
        deposit.setMessage(message);
        depositRepository.save(deposit);
    }

    @Override
    public List<TransactionHistoryResponseDTO> getTransactionHistory(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        // get accountId from request
        // get currently authenticated customer
        String jwt = aes.decryptText("AES", authorizationHeader.substring(7));
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();


        Long checkCustomerId = Long.valueOf((Integer) claims.get("customerId"));
        Account loggedInCustomerAccount = accountRepository.fetchByCustomerId(checkCustomerId);

        return setterForTransactionHistoryResponseDTO(depositRepository.getAllDepositFrom(loggedInCustomerAccount.getId()));
    }

    private List<TransactionHistoryResponseDTO> setterForTransactionHistoryResponseDTO(List<Deposit> depositList){
        List<TransactionHistoryResponseDTO> transactionHistoryResponseDTOList  = new ArrayList<>();
        for(Deposit d : depositList){
            TransactionHistoryResponseDTO transactionHistoryResponseDTO = new TransactionHistoryResponseDTO();
            transactionHistoryResponseDTO.setAmount(d.getAmount());
            transactionHistoryResponseDTO.setFrom(aes.decryptText("AES",customerRepository.fetchByAccountNumber(d.getFrom().getAccountNumber()).getMobile()));
            transactionHistoryResponseDTO.setTo(aes.decryptText("AES",customerRepository.fetchByAccountNumber(d.getTo().getAccountNumber()).getMobile()));
            transactionHistoryResponseDTO.setClientDateTime(d.getClientDateTime());
            transactionHistoryResponseDTO.setMessage(d.getMessage());
            transactionHistoryResponseDTOList.add(transactionHistoryResponseDTO);
            // get minutes/seconds ago
//            Instant now = Instant.now();  // Capture the current moment as seen in UTC.
//            Instant then = now.minus( 8L , ChronoUnit.HOURS ).minus( 8L , ChronoUnit.MINUTES ).minus( 8L , ChronoUnit.SECONDS );
//            Duration duration = Duration.between( then , now );
//            transactionHistoryResponseDTO.setAgo("");
        }
        return transactionHistoryResponseDTOList;
    }



}
