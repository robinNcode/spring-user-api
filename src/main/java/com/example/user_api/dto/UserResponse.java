package com.example.user_api.dto;

/**
 * Represents the public user representation returned by the API.
 */
public record UserResponse(
        Long id,
        String name,
        String email
) {
}