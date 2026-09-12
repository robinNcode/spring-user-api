package com.example.user_api.repository;

import com.example.user_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Provides persistence operations for User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by email address.
     *
     * @param email user's email
     * @return matching user if present
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks whether a user exists with the specified email.
     *
     * @param email user's email
     * @return true when email already exists
     */
    boolean existsByEmail(String email);
}