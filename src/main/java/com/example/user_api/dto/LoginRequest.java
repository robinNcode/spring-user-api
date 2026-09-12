package com.example.user_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents login credentials supplied by the client.
 */
public record LoginRequest(
        @NotBlank
        @Email
        String email,

        @NotBlank
        String password
) {

}