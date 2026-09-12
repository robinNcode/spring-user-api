package com.example.user_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

/**
 * Handles application exceptions globally.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles missing resources.
     *
     * @param exception resource-not-found exception
     * @return HTTP 404 response
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(
            ResourceNotFoundException exception
    ) {

        return Map.of(
                "timestamp", Instant.now(),
                "status", 404,
                "message", exception.getMessage()
        );
    }

    /**
     * Handles invalid business input.
     *
     * @param exception illegal argument exception
     * @return HTTP 400 response
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBadRequest(
            IllegalArgumentException exception
    ) {

        return Map.of(
                "timestamp", Instant.now(),
                "status", 400,
                "message", exception.getMessage()
        );
    }
}