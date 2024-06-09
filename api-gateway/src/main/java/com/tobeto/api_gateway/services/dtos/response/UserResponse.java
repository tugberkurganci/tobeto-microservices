package com.tobeto.api_gateway.services.dtos.response;

import com.tobeto.api_gateway.entities.User;

public record UserResponse(int id, String role, String email) {

    public UserResponse(User user) {
        this(
                user.getId(),
                String.valueOf(user.getRole()),
                user.getEmail()
        );
    }
}