package com.tobeto.api_gateway.services.abstracts;


import com.tobeto.api_gateway.entities.Token;
import com.tobeto.api_gateway.entities.User;
import com.tobeto.api_gateway.services.dtos.request.RefreshTokenRequest;
import io.jsonwebtoken.*;


public interface JwtTokenService {
    Token CreateToken(User user, boolean isRefresh);
    User verifyToken(String authorizationHeader);
    Token createRefeshToken(RefreshTokenRequest request);
    void deleteToken(int id);
}
