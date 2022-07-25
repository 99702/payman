package com.payman.service;

import com.payman.dto.request.RegisterAAccountRequestDTO;
import com.payman.dto.response.ListAllAccountResponse;
import com.payman.dto.response.RegisterAAccountDTOResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AccountService {
    RegisterAAccountDTOResponse registerAAccount(RegisterAAccountRequestDTO registerAAccountRequestDTO);

    List<ListAllAccountResponse> listAllAccount(HttpServletRequest request);
}
