package com.whispr.security.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsernameService {

    public String getUsername(UUID userId) {
        // TODO: Implement the logic to retrieve the username based on the userId.
        if (userId == null) {
            return "Unknown User";
        }

        // For now, we return a placeholder string.
        return "User " + userId.toString().substring(0, 3);
    }
}
