package com.example.user_api.service.impl;

import com.example.user_api.dto.UserCreateRequest;
import com.example.user_api.dto.UserResponse;
import com.example.user_api.entity.User;
import com.example.user_api.exception.ResourceNotFoundException;
import com.example.user_api.repository.UserRepository;
import com.example.user_api.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of user business operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Creates a new user after validating email uniqueness
     * and securely hashing the password.
     *
     * @param request user creation request
     * @return created user response
     */
    @Override
    public UserResponse create(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();

        return toResponse(userRepository.save(user));
    }

    /**
     * Retrieves all users.
     *
     * @return list of user responses
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id user ID
     * @return user response
     */
    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + id)
                );

        return toResponse(user);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id user ID
     */
    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found: " + id);
        }

        userRepository.deleteById(id);
    }

    /**
     * Converts a User entity into a public API response.
     *
     * @param user user entity
     * @return user response
     */
    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}