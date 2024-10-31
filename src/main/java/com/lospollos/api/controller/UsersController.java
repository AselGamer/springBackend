package com.lospollos.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lospollos.api.model.User;
import com.lospollos.api.repository.UserRepository;

@RestController
@RequestMapping(path = "/api/users")
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Error usuario no encontrado"));
    }
}
