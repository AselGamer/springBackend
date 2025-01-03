package com.lospollos.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lospollos.api.exception.RestFailureException;
import com.lospollos.api.model.User;
import com.lospollos.api.repository.UserRepository;
import com.lospollos.api.security.JwtUtil;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RestFailureException("Error usuario no encontrado"));
    }

    @GetMapping(path = "/profile")
    public User getUserProfile(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
        return userRepository.findByEmail(jwtUtil.getEmailFromToken(token));
    }
}
