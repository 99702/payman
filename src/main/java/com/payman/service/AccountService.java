package com.payman.service;

import com.payman.dto.request.RegisterAAccountRequestDTO;
import com.payman.dto.response.RegisterAAccountDTOResponse;

public interface AccountService {
    RegisterAAccountDTOResponse registerAAccount(RegisterAAccountRequestDTO registerAAccountRequestDTO, Long customerId);
}
