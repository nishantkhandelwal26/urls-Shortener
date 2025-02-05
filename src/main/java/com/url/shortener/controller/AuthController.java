package com.url.shortener.controller;

import com.url.shortener.DTOs.LoginRequest;
import com.url.shortener.DTOs.RegisterRequest;
import com.url.shortener.models.User;
import com.url.shortener.security.jwt.JwtAuthenticationResponse;
import com.url.shortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/public/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        JwtAuthenticationResponse response = userService.authenticateUser(loginRequest);
        System.out.println("Logged in");
        System.out.println(response);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setRole("ROLE_USER");
        user.setEmail(registerRequest.getEmail());
        userService.registerUser(user);
        return ResponseEntity.ok("User Registered successfully!");
    }
}
