package com.payman.service.impl;

import com.payman.dto.request.RegisterACustomerDTO;
import com.payman.dto.response.GetACustomerDetails;
import com.payman.dto.response.RegisterACustomerDTOResponse;
import com.payman.entity.Account;
import com.payman.entity.Balance;
import com.payman.entity.Customer;
import com.payman.errors.PaymanException;
import com.payman.repository.AccountRepository;
import com.payman.repository.BalanceRepository;
import com.payman.repository.CustomerRepository;
import com.payman.service.CustomerService;
import com.payman.utils.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BalanceRepository balanceRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AES aes;

    @Override
    public RegisterACustomerDTOResponse registerACustomer(@RequestBody RegisterACustomerDTO registerACustomerDTO) {
        Customer customer = new Customer();
        customer.setCitizenshipNo(registerACustomerDTO.getCitizenshipNo());
        customer.setDob(registerACustomerDTO.getDob());
        customer.setFullName(registerACustomerDTO.getFullName());
        customer.setMobile(aes.encryptText("AES",registerACustomerDTO.getMobile()));
        customer.setProvince(registerACustomerDTO.getProvince());
        customer.setPassword(aes.encryptText("AES",passwordEncoder.encode(registerACustomerDTO.getPassword())));
        customer.setGender(registerACustomerDTO.getGender());
        customerRepository.save(customer);

        return this.setterForRegisterACustomerDTOResponse("Registered successfully ", "success", customer.getFullName());
    }

    @Override
    public GetACustomerDetails getCustomerFromAccNo(String accountNumber) {
        try{
            // get customer from that accountNumber
            Customer customer = customerRepository.fetchByAccountNumber(aes.encryptText("AES", accountNumber));
            //basically if customer doesn't exists
            if(customer == null){
                throw new PaymanException("Customer doesn't exists ");
            }
            return this.setterForGetACustomerDetails(customer);
        } catch(Exception e){
            throw new PaymanException(e.getMessage());
        }
    }

    private RegisterACustomerDTOResponse setterForRegisterACustomerDTOResponse(String message, String status, String fullName){
        RegisterACustomerDTOResponse registerACustomerDTOResponse = new RegisterACustomerDTOResponse();
        registerACustomerDTOResponse.setMessage(message);
        registerACustomerDTOResponse.setStatus(status);
        registerACustomerDTOResponse.setFullName(fullName);
        return registerACustomerDTOResponse;
    }

    private GetACustomerDetails setterForGetACustomerDetails(Customer customer){
        GetACustomerDetails getACustomerDetails = new GetACustomerDetails();
        Account account = accountRepository.fetchByCustomerId(customer.getId());
        Balance balance = balanceRepository.fetchByAccountId(account.getId());
        getACustomerDetails.setBalance(balance.getBalance());
        getACustomerDetails.setGender(customer.getGender() == null ? "UNKNOWN": String.valueOf(customer.getGender()));
        getACustomerDetails.setFull_name(customer.getFullName());
        getACustomerDetails.setMobile(aes.decryptText("AES",customer.getMobile()));
        getACustomerDetails.setCitizenship_number(customer.getCitizenshipNo());
        getACustomerDetails.setDob(customer.getDob());
        return getACustomerDetails;
    }
}
