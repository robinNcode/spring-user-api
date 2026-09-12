package com.example.user_api.controller;

import com.example.user_api.dto.LoginRequest;
import com.example.user_api.dto.LoginResponse;
import com.example.user_api.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for authentication endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Authenticates a user and generates a JWT.
     *
     * @param request login credentials
     * @return JWT authentication response
     */
    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
}