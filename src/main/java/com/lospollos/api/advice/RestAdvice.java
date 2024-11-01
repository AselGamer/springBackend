package com.lospollos.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lospollos.api.exception.RestFailureException;
import com.lospollos.api.service.ResponseService;

@RestControllerAdvice
public class RestAdvice {
    
    @ExceptionHandler(RestFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> restFailureHandler(RestFailureException ex) {
        return ResponseService.toJsonResponse(ex.getMessage(), "error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
