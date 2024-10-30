package com.lospollos.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lospollos.api.model.User;
import com.lospollos.api.repository.UserRepository;

@Controller
@RequestMapping(path = "/users")
public class UsersController {
    private UserRepository userRepository;

    @GetMapping(path = "/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
