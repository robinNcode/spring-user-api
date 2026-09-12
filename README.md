# User Service 

## Description
The User Service is a microservice responsible for managing user-related operations in the application. It provides
    functionalities such as user registration, authentication, profile management, and user data retrieval. The service is designed to be scalable, secure, and easy to integrate with other services in the application ecosystem.

## Features
- User Registration: Allows new users to create an account by providing necessary information such as username,
  - email, and password.
- User Authentication: Supports user login and authentication using secure methods, including JWT (JSON Web Tokens
- Password Management: Enables users to change their passwords and recover forgotten passwords through secure processes.
- Profile Management: Allows users to update their profile information
- User Data Retrieval: Provides endpoints to retrieve user data for various purposes, such as displaying user profiles or fetching user-related information for other services.
- Security: Implements security best practices, including password hashing, input validation, and protection against common vulnerabilities.

## API Endpoints
The User Service exposes the following API endpoints:
- `POST /users/register`: Registers a new user.
- `POST /users/login`: Authenticates a user and returns a JWT token.
- `GET /users/:id`: Retrieves user information by user ID.
- `PUT /users/:id`: Updates user profile information.
- `POST /users/:id/change-password`: Allows users to change their password.
- `POST /users/:id/forgot-password`: Initiates the password recovery process for users who have forgotten their password.
- `GET /users`: Retrieves a list of all users (admin access required).
- `DELETE /users/:id`: Deletes a user account (admin access required).
- `GET /users/search`: Searches for users based on specific criteria (admin access required).
- `GET /users/:id/roles`: Retrieves the roles assigned to a user (admin access required).
- `POST /users/:id/roles`: Assigns roles to a user (admin access required).
- `DELETE /users/:id/roles`: Removes roles from a user (admin access required).
- `GET /users/:id`: Retrieves user information by user ID.
- `PUT /users/:id`: Updates user profile information.


