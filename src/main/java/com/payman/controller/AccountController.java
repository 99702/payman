package com.payman.controller;

import com.payman.dto.request.RegisterAAccountRequestDTO;
import com.payman.dto.response.RegisterAAccountDTOResponse;
import com.payman.enumeration.PathConstants;
import com.payman.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping(PathConstants.REGISTER_ACCOUNT)
    private RegisterAAccountDTOResponse registerAAccount(@RequestBody @Valid RegisterAAccountRequestDTO registerAAccountRequestDTO, @PathVariable("cId") Long customerId){
        return accountService.registerAAccount(registerAAccountRequestDTO, customerId);
    }
}

