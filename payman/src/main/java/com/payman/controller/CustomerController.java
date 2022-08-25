package com.payman.controller;

import com.payman.dto.request.RegisterACustomerDTO;
import com.payman.dto.response.GetACustomerDetails;
import com.payman.dto.response.RegisterACustomerDTOResponse;
import com.payman.enumeration.PathConstants;
import com.payman.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping(PathConstants.REGISTER_CUSTOMER)
    private RegisterACustomerDTOResponse registerACustomer(@Valid @RequestBody RegisterACustomerDTO registerACustomerDTO){
        return customerService.registerACustomer(registerACustomerDTO);
    }

    @GetMapping(PathConstants.GET_A_CUSTOMER_FROM_ACCOUNT_ID)
    private GetACustomerDetails getACustomerDetails(@PathVariable("acc_no") String accountNumber){
        return customerService.getCustomerFromAccNo(accountNumber);
    }
}