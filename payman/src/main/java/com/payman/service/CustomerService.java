package com.payman.service;

import com.payman.dto.request.RegisterACustomerDTO;
import com.payman.dto.response.GetACustomerDetails;
import com.payman.dto.response.RegisterACustomerDTOResponse;

public interface CustomerService {
    RegisterACustomerDTOResponse registerACustomer(RegisterACustomerDTO registerACustomerDTOResponse);

    GetACustomerDetails getCustomerFromAccNo(String accountNumber);
}
