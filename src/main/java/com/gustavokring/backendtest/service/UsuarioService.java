package com.gustavokring.backendtest.service;

import com.gustavokring.backendtest.entities.Usuario;
import com.gustavokring.backendtest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario insertNewUsuario(String login, String senha, String nome, boolean administrador) {

        return repository.save(
                Usuario.builder()
                        .login(login)
                        .senha(senha)
                        .nome(nome)
                        .administrador(administrador)
                        .build()
        );
    }
}
