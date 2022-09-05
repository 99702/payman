package com.payman.service;

import com.payman.dto.request.RegisterACustomerDTO;
import com.payman.dto.request.ResetPasswordRequestDTO;
import com.payman.dto.response.ResetPasswordResponseDto;
import com.payman.dto.request.UpdateCustomerRequest;
import com.payman.dto.response.GetACustomerDetails;
import com.payman.dto.response.RegisterACustomerDTOResponse;
import com.payman.dto.response.UpdateSuccess;

import javax.servlet.http.HttpServletRequest;

public interface CustomerService {
    RegisterACustomerDTOResponse registerACustomer(RegisterACustomerDTO registerACustomerDTOResponse);

    GetACustomerDetails getCustomerFromAccNo(String accountNumber);

    UpdateSuccess updateCustomer(UpdateCustomerRequest updateCustomerRequest, HttpServletRequest request);

    String savePasswordResetToken(String mobile, String token);

    ResetPasswordResponseDto resetPasswordDone(ResetPasswordRequestDTO resetPasswordRequestDTO, String token);
}
