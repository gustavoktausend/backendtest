package com.gustavokring.backendtest.repository;

import com.gustavokring.backendtest.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByLogin(String login);

    Optional<Token> findByToken(String token);

}
