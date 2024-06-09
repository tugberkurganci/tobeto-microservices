package com.tobeto.api_gateway.services.concretes;

import com.tobeto.api_gateway.entities.Role;
import com.tobeto.api_gateway.entities.User;
import com.tobeto.api_gateway.repositories.UserRepository;
import com.tobeto.api_gateway.services.abstracts.UserService;
import com.tobeto.api_gateway.services.dtos.request.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {

    private  UserRepository userRepository;
    private PasswordEncoder passwordEncoder;



    public UserManager(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getEntityUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("user.notfound"));
    }
    @Override
    public void add(CreateUserRequest createUserRequest) {

        User user = User.builder()
                .email(createUserRequest.email())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);    }

}
