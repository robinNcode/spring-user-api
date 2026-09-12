package com.example.user_api.exception;

/**
 * Exception thrown when a requested resource does not exist.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Creates a resource-not-found exception.
     *
     * @param message error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}