package com.payman.controller;

import com.payman.dto.request.LoginRequestDTO;
import com.payman.dto.response.CurrentUserResponseDTO;
import com.payman.dto.response.LoginResponseDTO;
import com.payman.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

}