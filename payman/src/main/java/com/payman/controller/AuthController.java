package com.payman.controller;

import com.payman.dto.request.LoginRequestDTO;
import com.payman.dto.response.CurrentUserResponseDTO;
import com.payman.dto.response.LoginHistoryResponse;
import com.payman.dto.response.LoginResponseDTO;
import com.payman.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("login")
    private LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest request){
       return authService.login(loginRequestDTO, request);
    }

    @GetMapping("current")
    private CurrentUserResponseDTO getCurrentLoggedinUser(HttpServletRequest request){
        return authService.getCurrentLoggedinUser(request);
    }

    @GetMapping("history")
    private List<LoginHistoryResponse> getLoginHistory(HttpServletRequest request){
        return authService.getLoginHistory(request);
    }


}