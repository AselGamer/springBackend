package com.lospollos.api.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    public static ResponseEntity<String> toJsonResponse(String body, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return new ResponseEntity<>("{\"msg\":\""+body+"\"}", headers, status);
    }

    public static ResponseEntity<String> toJsonResponse(String body, String key, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        return new ResponseEntity<>("{\""+key+"\":\""+body+"\"}", headers, status);
    }
}
