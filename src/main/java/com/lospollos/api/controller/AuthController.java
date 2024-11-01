package com.lospollos.api.controller;
import com.lospollos.api.model.User;
import com.lospollos.api.repository.UserRepository;
import com.lospollos.api.security.JwtUtil;
import com.lospollos.api.service.ResponseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getFirstname(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseService.toJsonResponse(jwtUtils.generateToken(userDetails.getUsername()), "token", HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (userRepository.existsByFirstname(user.getFirstname())) {
            return ResponseService.toJsonResponse("Username is already taken!", "error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Create new user's account
        User newUser = new User(
                user.getFirstname(),
                user.getLastname(),
                encoder.encode(user.getPassword())
        );
        userRepository.save(newUser);
        return ResponseService.toJsonResponse("User registered successfully!", "error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
