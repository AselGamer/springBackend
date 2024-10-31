package com.lospollos.api.exceptions;

public class RestFailureException extends RuntimeException {
    
    public RestFailureException(String msg) {
        super(msg);
    }
}
