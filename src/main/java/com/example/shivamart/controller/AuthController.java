package com.example.shivamart.controller;

import com.example.shivamart.dto.AuthResponse;
import com.example.shivamart.dto.LoginRequest;
import com.example.shivamart.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        String token =
                userService.login(
                        request.getEmail(),
                        request.getPassword());

        return new AuthResponse(token);
    }
}