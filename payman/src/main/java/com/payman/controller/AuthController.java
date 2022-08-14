package com.payman.controller;

import com.payman.dto.request.LoginRequestDTO;
import com.payman.dto.response.LoginResponseDTO;
import com.payman.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("login")
    private LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO, HttpServletRequest request){
       return authService.login(loginRequestDTO, request);
    }

}