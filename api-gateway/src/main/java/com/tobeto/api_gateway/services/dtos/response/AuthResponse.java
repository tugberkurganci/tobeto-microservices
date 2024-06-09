package com.tobeto.api_gateway.services.dtos.response;


import com.tobeto.api_gateway.entities.Token;

public record AuthResponse(
        UserResponse user,
        Token token
) {
}

