package com.tobeto.api_gateway.services.abstracts;


import com.tobeto.api_gateway.entities.User;
import com.tobeto.api_gateway.services.dtos.request.CreateUserRequest;

public interface UserService {


    User findByEmail(String username);

    User getEntityUserById(int userId);

    void add(CreateUserRequest createUserRequest);

}