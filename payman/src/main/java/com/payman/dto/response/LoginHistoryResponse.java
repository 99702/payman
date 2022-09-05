package com.payman.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoginHistoryResponse {
    private String address;
    private LocalDateTime createdAt;
    private Integer totalPageSize;
    private Long totalElements;

}
