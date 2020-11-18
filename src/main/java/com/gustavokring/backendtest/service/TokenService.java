package com.gustavokring.backendtest.service;

import com.gustavokring.backendtest.entities.Token;
import com.gustavokring.backendtest.repository.TokenRepository;
import com.gustavokring.backendtest.repository.UsuarioRepository;
import com.gustavokring.backendtest.util.TokenGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class TokenService {
    @Autowired
    private TokenRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean reautenticarToken(String tokenStr) {
        return repository.findByToken(tokenStr)
                .map(token ->
                        !repository.save(token.withExpiracao(Timestamp.from(Instant.now().plusSeconds(300)))).isExpired()
                ).orElse(false);
    }

    public Token createToken(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, senha)
                .map(usuario ->
                    repository.save(
                            Token.builder()
                                    .login(usuario.getLogin())
                                    .administrador(usuario.isAdministrador())
                                    .token(new TokenGeneratorUtil().generateToken())
                                    .expiracao(Timestamp.from(Instant.now().plusSeconds(300)))
                                    .build()
                    )
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }

}
