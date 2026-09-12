package com.example.user_api;

import com.example.user_api.dto.UserCreateRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserControllerValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void shouldReturnValidationErrorForInvalidUserPayload() {
        var request = new UserCreateRequest("", "bad-email", "123", 1L);

        var violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        var messages = violations.stream()
                .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                .toList();

        assertTrue(messages.stream().anyMatch(message -> message.contains("name")));
        assertTrue(messages.stream().anyMatch(message -> message.contains("email")));
        assertTrue(messages.stream().anyMatch(message -> message.contains("password")));
    }
}
