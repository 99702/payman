package com.payman.service.impl;

import com.payman.dto.request.RegisterAAccountRequestDTO;
import com.payman.dto.response.RegisterAAccountDTOResponse;
import com.payman.entity.Account;
import com.payman.entity.Balance;
import com.payman.entity.Customer;
import com.payman.errors.PaymanException;
import com.payman.repository.AccountRepository;
import com.payman.repository.BalanceRepository;
import com.payman.repository.CustomerRepository;
import com.payman.service.AccountService;
import com.payman.utils.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AES aes;

    @Autowired
    BalanceRepository balanceRepository;

    @Override
    public RegisterAAccountDTOResponse registerAAccount(RegisterAAccountRequestDTO registerAAccountRequestDTO, Long customerId) {
        try{
            // check if customer of that userId exists
            Customer customer  = customerRepository.fetchById(customerId);
            if(customer == null){
                throw new PaymanException("Customer doesn't exists");
            }

            //create random account id, check if that random account id already exists
            Long rnd = Math.abs(new Random ().nextLong());
            String encryptedRandom = aes.encryptText("AES", String.valueOf(rnd));

            if(accountRepository.fetchByAccountNo(encryptedRandom) != null){
               throw new PaymanException("Account number already exists");
            }

            // check if account already exists for that customer
            if(accountRepository.fetchByCustomerId(customer.getId()) != null){
               throw  new PaymanException("customer have already account_number registered");
            }

            // now save account
            Account account = new Account();
            account.setAccountNumber(encryptedRandom);
            account.setCustomer(customer);
            Account savedAccount = accountRepository.save(account);

            // check if account have already balance
//            if(balanceRepository.fetchByAccountId(savedAccount.getId()) != null){
//               throw new PaymanException("User is registered but customer already have balance");
//            }

            // automatically associate balance table with that customer id while creating account
            Balance balance = new Balance();
            balance.setAccount(savedAccount);
            balance.setBalance(BigDecimal.valueOf(0.0));
            Balance balance1 = balanceRepository.save(balance);
            return this.setterForRegisterAAccountDTOResponse(account, balance1, customer);

        } catch (Exception e){
           throw  new PaymanException(e.getMessage());
        }
    }
    private RegisterAAccountDTOResponse setterForRegisterAAccountDTOResponse(Account account, Balance balance, Customer customer){
        RegisterAAccountDTOResponse registerAAccountDTOResponse = new RegisterAAccountDTOResponse();
        registerAAccountDTOResponse.setAccount_number(aes.decryptText("AES", account.getAccountNumber()));
        registerAAccountDTOResponse.setMobile(aes.decryptText("AES",customer.getMobile()));
        registerAAccountDTOResponse.setBalance(balance.getBalance());
        registerAAccountDTOResponse.setFullName(customer.getFullName());
        registerAAccountDTOResponse.setSuccess(true);
        return registerAAccountDTOResponse;
    }
}
