package com.example.user_api.service;

import com.example.user_api.dto.LoginRequest;
import com.example.user_api.dto.LoginResponse;

/**
 * Defines authentication-related business operations.
 */
public interface AuthService {

    /**
     * Authenticates a user and generates an access token.
     *
     * @param request login credentials
     * @return authentication response
     */
    LoginResponse login(LoginRequest request);
}