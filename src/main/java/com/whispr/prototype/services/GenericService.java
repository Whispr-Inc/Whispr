package com.whispr.prototype.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenericService {

    public String getUserFullNameUsingId(UUID userId) {
        // This method should be implemented to retrieve the user's full name based on their ID.
        // For now, we return a placeholder string.

        if (userId == null) {
            return "Unknown User";
        }

        return "User " + userId.toString().substring(0, 3);
    }
}
