package com.tobeto.api_gateway.services.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public record CreateUserRequest(


        @NotBlank(message = "{email.notblank}")
        @Email(message = "{email.invalidformat}")
        String email,

        @NotBlank(message = "{password.notblank}")
        @Size(min = 6, message = "{password.size}")
        String password

) {}
