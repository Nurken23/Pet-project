package com.example.booking_system.controller;

import com.example.booking_system.dto.LoginRequest;
import com.example.booking_system.dto.LoginResponse;
import com.example.booking_system.model.User;
import com.example.booking_system.service.AuthService;
import com.example.booking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}