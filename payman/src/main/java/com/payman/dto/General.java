package com.payman.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class General {
    private String status;
    private String message;
}
