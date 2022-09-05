package com.payman.controller;

import com.payman.dto.General;
import com.payman.dto.request.MobileDto;
import com.payman.dto.request.RegisterACustomerDTO;
import com.payman.dto.request.ResetPasswordRequestDTO;
import com.payman.dto.response.ResetPasswordResponseDto;
import com.payman.dto.request.UpdateCustomerRequest;
import com.payman.dto.response.GetACustomerDetails;
import com.payman.dto.response.RegisterACustomerDTOResponse;
import com.payman.dto.response.UpdateSuccess;
import com.payman.enumeration.PathConstants;
import com.payman.event.ResetPasswordSendEvent;
import com.payman.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping(PathConstants.REGISTER_CUSTOMER)
    private RegisterACustomerDTOResponse registerACustomer(@Valid @RequestBody RegisterACustomerDTO registerACustomerDTO){
        return customerService.registerACustomer(registerACustomerDTO);
    }

    @GetMapping(PathConstants.GET_A_CUSTOMER_FROM_ACCOUNT_ID)
    private GetACustomerDetails getACustomerDetails(@PathVariable("acc_no") String accountNumber){
        return customerService.getCustomerFromAccNo(accountNumber);
    }

    @PostMapping(PathConstants.UPDATE_A_CUSTOMER)
    private UpdateSuccess updateCustomer(@RequestBody UpdateCustomerRequest updateCustomerRequest, HttpServletRequest request){return customerService.updateCustomer(updateCustomerRequest, request);
    }

    @PostMapping(PathConstants.RESET_PASSWORD)
    private General resetPassword(@RequestBody MobileDto mobile, HttpServletRequest request) {
        publisher.publishEvent(new ResetPasswordSendEvent(mobile, applicationUrl(request)));
        return General.builder().message("Reset password token have been sent to your mobile number.").status("ok").build();
    }

    @PostMapping("reset/{token}")
    private ResetPasswordResponseDto resetPasswordDone(@Valid @RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO, @PathVariable("token") String token){
        return customerService.resetPasswordDone(resetPasswordRequestDTO, token);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}