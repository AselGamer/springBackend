package com.lospollos.api.exception;

public class RestFailureException extends RuntimeException {
    
    public RestFailureException(String msg) {
        super(msg);
    }
}
