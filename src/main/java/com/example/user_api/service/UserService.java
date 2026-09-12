package com.example.user_api.service;

import com.example.user_api.dto.UserCreateRequest;
import com.example.user_api.dto.UserResponse;

import java.util.List;

/**
 * Defines business operations related to users.
 */
public interface UserService {

    /**
     * Creates a new user.
     *
     * @param request user creation data
     * @return created user
     */
    UserResponse create(UserCreateRequest request);

    /**
     * Returns all users.
     *
     * @return list of users
     */
    List<UserResponse> findAll();

    /**
     * Finds a user by identifier.
     *
     * @param id user identifier
     * @return user
     */
    UserResponse findById(Long id);

    /**
     * Deletes a user.
     *
     * @param id user identifier
     */
    void delete(Long id);
}