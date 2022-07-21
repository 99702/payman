package com.payman.service;

import com.payman.dto.request.LoginRequestDTO;
import com.payman.dto.response.LoginResponseDTO;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO, HttpServletRequest request);
}
