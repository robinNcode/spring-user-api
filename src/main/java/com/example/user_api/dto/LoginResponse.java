package com.example.user_api.dto;

/**
 * Represents the successful authentication response.
 */
public record LoginResponse(
        String accessToken,
        String tokenType
) {
}