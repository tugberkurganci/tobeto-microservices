package com.tobeto.api_gateway.services.abstracts;


import com.tobeto.api_gateway.services.dtos.request.Credentials;
import com.tobeto.api_gateway.services.dtos.response.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(Credentials credentials);

}
