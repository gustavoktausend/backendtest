package com.gustavokring.backendtest.service;

import com.gustavokring.backendtest.dto.UsuarioAutenticado;
import com.gustavokring.backendtest.entities.Token;
import com.gustavokring.backendtest.entities.Usuario;
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
public class UsuarioService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenRepository tokenRepository;

    public Usuario insertNewUsuario(String login, String senha, String nome, boolean administrador) {

         if(repository.findByLogin(login).isEmpty()) {
             return repository.save(
                     Usuario.builder()
                             .login(login)
                             .senha(senha)
                             .nome(nome)
                             .administrador(administrador)
                             .build()
             );
         } else throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    }

    public UsuarioAutenticado autenticateUser(String login, String senha) {
        return repository.findByLoginAndSenha(login, senha)
                .map(usuario -> {
                    Token tokenAuth = tokenRepository.findByLogin(usuario.getLogin())
                            .map(token -> {
                                if(token.isExpired()) tokenService.reautenticarToken(token.getToken());
                                return token; })
                            .orElseGet(() -> tokenRepository.save(
                                    Token.builder()
                                            .login(usuario.getLogin())
                                            .administrador(usuario.isAdministrador())
                                            .token(new TokenGeneratorUtil().generateToken())
                                            .expiracao(Timestamp.from(Instant.now().plusSeconds(300)))
                                            .build()
                            ));
                            return UsuarioAutenticado.builder()
                                    .administrador(usuario.isAdministrador())
                                    .login(usuario.getLogin())
                                    .nome(usuario.getNome())
                                    .token(tokenAuth.getToken())
                                    .autenticado(true).build();
                        }
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }
}
