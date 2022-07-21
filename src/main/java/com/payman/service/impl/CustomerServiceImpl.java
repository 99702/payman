package com.payman.service.impl;

import com.payman.dto.request.RegisterACustomerDTO;
import com.payman.dto.response.RegisterACustomerDTOResponse;
import com.payman.entity.Customer;
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

    private RegisterACustomerDTOResponse setterForRegisterACustomerDTOResponse(String message, String status, String fullName){
        RegisterACustomerDTOResponse registerACustomerDTOResponse = new RegisterACustomerDTOResponse();
        registerACustomerDTOResponse.setMessage(message);
        registerACustomerDTOResponse.setStatus(status);
        registerACustomerDTOResponse.setFullName(fullName);
        return registerACustomerDTOResponse;
    }
}
