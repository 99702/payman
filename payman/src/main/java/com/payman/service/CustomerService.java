package com.payman.service;

import com.payman.dto.request.RegisterACustomerDTO;
import com.payman.dto.request.UpdateCustomerRequest;
import com.payman.dto.response.GetACustomerDetails;
import com.payman.dto.response.RegisterACustomerDTOResponse;
import com.payman.dto.response.UpdateSuccess;

import javax.servlet.http.HttpServletRequest;

public interface CustomerService {
    RegisterACustomerDTOResponse registerACustomer(RegisterACustomerDTO registerACustomerDTOResponse);

    GetACustomerDetails getCustomerFromAccNo(String accountNumber);

    UpdateSuccess updateCustomer(UpdateCustomerRequest updateCustomerRequest, HttpServletRequest request);
}
