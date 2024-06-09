package com.tobeto.api_gateway.repositories;

import com.tobeto.api_gateway.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String mail);

    User findByEmail(String email);



}