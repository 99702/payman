package com.payman.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorMessage {
    HttpStatus status;
    String message;
}
