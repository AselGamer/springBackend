package com.lospollos.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lospollos.api.exceptions.RestFailureException;

@RestControllerAdvice
public class RestAdvice {
    
    @ExceptionHandler(RestFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String restFailureHandler(RestFailureException ex) {
        return ex.getMessage();
    }
}
