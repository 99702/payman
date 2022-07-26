package com.payman.service.impl;

import com.payman.dto.request.DepositRequestDTO;
import com.payman.dto.response.BalanceDepositResponse;
import com.payman.dto.response.BalanceWithdrawResponse;
import com.payman.entity.Account;
import com.payman.entity.Balance;
import com.payman.entity.Customer;
import com.payman.errors.PaymanException;
import com.payman.repository.AccountRepository;
import com.payman.repository.BalanceRepository;
import com.payman.repository.CustomerRepository;
import com.payman.service.BalanceService;
import com.payman.service.DepositService;
import com.payman.utils.AES;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class BalanceServiceImpl implements BalanceService {
    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    DepositService depositService;

    @Autowired
    AES aes;


    @Override
    public BalanceWithdrawResponse withdraw(HttpServletRequest request) {
        return null;
    }



    @Override
    @Transactional
    public BalanceDepositResponse deposit(HttpServletRequest request, DepositRequestDTO depositRequestDTO, String accountNumber, String mobileNumber) {
        BigDecimal balance = depositRequestDTO.getBalance();
        String authorizationHeader = request.getHeader("Authorization");
        try {
            if(accountNumber != null && mobileNumber != null || accountNumber == null && mobileNumber == null){
                throw new PaymanException("Something went wrong");
            }

            // Account No that we want to send our balance
            Account accountToBeSent = mobileNumber == null ? accountRepository.fetchByAccountNo(accountNumber) : accountRepository.fetchByMobileNumber(aes.encryptText("AES", mobileNumber));
            if(accountToBeSent == null){
               throw new PaymanException("Account doesn't exists");
            }

            // get currently authenticated customer
            String jwt = aes.decryptText("AES", authorizationHeader.substring(7));
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();

            Long checkCustomerId = Long.valueOf((Integer) claims.get("customerId"));

            // now check balance of loggedinCustomer
            Balance balanceOfLoggedinUser =  balanceRepository.fetchBalanceByCustomerId(checkCustomerId) ;
            Customer loggedInCustomer = customerRepository.fetchById(checkCustomerId);
            Account loggedInCustomerAccount = accountRepository.fetchByCustomerId(loggedInCustomer.getId());


            // our balance is less than given balance
            if(balanceOfLoggedinUser.getBalance().compareTo(balance) < 0){
                throw new PaymanException("Insufficient balance");
            }
            // given and our balance are same || our balance is greater than given balance
           if(balanceOfLoggedinUser.getBalance().compareTo(balance) == 0 || balanceOfLoggedinUser.getBalance().compareTo(balance) > 0 ) {

               // deduct balance from loggedIn customer
               balanceOfLoggedinUser.setBalance(balanceOfLoggedinUser.getBalance().subtract(balance));
               balanceRepository.save(balanceOfLoggedinUser);


               // if request balance is less than 0
//               int a = 1/0;

               // add balance to that account number/mobile (That person) he given
               Balance balanceOfToCustomer =  balanceRepository.fetchByAccountId(accountToBeSent.getId()) ;
               balanceOfToCustomer.setBalance(balanceOfToCustomer.getBalance().add(balance));
               balanceRepository.save(balanceOfToCustomer);
           }

           // If everything is success we save details to deposit database
            depositService.depositData(
                    loggedInCustomerAccount,
                    accountToBeSent,
                    balance,
                    request.getHeader("ip"),
                    Double.parseDouble(request.getHeader("latitude")),
                    Double.parseDouble(request.getHeader("longitude")),
                    request.getHeader("screen"),
                    request.getHeader("userAgent"),
                    request.getHeader("clientDateTime"),
                    request.getHeader("language")
            );

           // save those to our dto response
            return this.setterForBalanceDepositResponse(loggedInCustomerAccount, accountToBeSent, balance, "success") ;

        } catch (Exception e){
                throw new PaymanException(e.getMessage());
        }
    }

    private BalanceDepositResponse setterForBalanceDepositResponse(Account fromAccount, Account toAccount, BigDecimal balance, String success) {
       BalanceDepositResponse balanceDepositResponse = new BalanceDepositResponse();
       balanceDepositResponse.setFrom_account(fromAccount.getAccountNumber());
       balanceDepositResponse.setTo_account(toAccount.getAccountNumber());
       balanceDepositResponse.setBalance(balance);
       balanceDepositResponse.setSuccess(success);
       return balanceDepositResponse;
    }
}
