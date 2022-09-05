package com.payman.service;

import com.payman.dto.request.LoginRequestDTO;
import com.payman.dto.response.CurrentUserResponseDTO;
import com.payman.dto.response.LoginHistoryResponse;
import com.payman.dto.response.LoginResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO, HttpServletRequest request);

    CurrentUserResponseDTO getCurrentLoggedinUser(HttpServletRequest request);

    List<LoginHistoryResponse> getLoginHistory(HttpServletRequest request, Integer pageNo);
}