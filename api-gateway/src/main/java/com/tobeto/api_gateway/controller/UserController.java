package com.tobeto.api_gateway.controller;


import com.tobeto.api_gateway.services.abstracts.UserService;
import com.tobeto.api_gateway.services.dtos.request.CreateUserRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateUserRequest createUserRequest){
        userService.add(createUserRequest);
    }}