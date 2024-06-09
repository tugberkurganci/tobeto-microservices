package com.tobeto.api_gateway.repositories;

import com.tobeto.api_gateway.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,String> {
    Token findByUserId(int i);

    void deleteByUserId(int id);
}
