package com.payman.errors;


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
