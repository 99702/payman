package com.payman.controller;

import com.payman.dto.request.RegisterAAccountRequestDTO;
import com.payman.dto.response.ListAllAccountResponse;
import com.payman.dto.response.RegisterAAccountDTOResponse;
import com.payman.enumeration.PathConstants;
import com.payman.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping(PathConstants.REGISTER_ACCOUNT)
    private RegisterAAccountDTOResponse registerAAccount(@RequestBody @Valid RegisterAAccountRequestDTO registerAAccountRequestDTO){
        return accountService.registerAAccount(registerAAccountRequestDTO);
    }

    @GetMapping(PathConstants.LIST_ALL_ACCOUNT)
    private List<ListAllAccountResponse> listAllAccount(HttpServletRequest request){
        return accountService.listAllAccount(request);
    }
}