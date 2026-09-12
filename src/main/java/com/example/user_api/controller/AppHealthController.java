package com.example.user_api.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/app/health", "/app/v1/health"})
@RequiredArgsConstructor
public class AppHealthController {

    private final DataSource dataSource;

    @GetMapping
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("application", "user-api");
        response.put("status", "UP");

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT 1");
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                response.put("database", "CONNECTED");
                response.put("dbStatus", "SUCCESS");
                response.put("message", "Application is running and database connection is healthy.");
            }
        } catch (Exception ex) {
            response.put("status", "DOWN");
            response.put("database", "DISCONNECTED");
            response.put("dbStatus", "FAILED");
            response.put("message", "Application is running but database connection failed.");
            response.put("error", ex.getMessage());
        }

        return response;
    }
}
