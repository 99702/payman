package com.payman.errors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

public class PaymanException extends RuntimeException {
//    public PaymanException(String exMessage, Exception exception) {
//        super(exMessage, exception);
//    }
    public PaymanException(String exMessage) {
        super(exMessage);
    }
    public  PaymanException(Map<String, String> mapMsg){}

}
