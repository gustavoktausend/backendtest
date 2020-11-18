package com.gustavokring.backendtest.service;

import com.gustavokring.backendtest.entities.Pais;
import com.gustavokring.backendtest.entities.Token;
import com.gustavokring.backendtest.repository.PaisRepository;
import com.gustavokring.backendtest.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaisService {

    @Autowired
    private PaisRepository repository;

    @Autowired
    private TokenRepository tokenRepository;

    public Pais insertPais(String nome, String sigla, String gentilico) {

        return repository.save(
                Pais.builder()
                        .nome(nome)
                        .sigla(sigla)
                        .gentilico(gentilico)
                        .build()
        );
    }

    public Pais insertPais(String token, Long id, String nome, String sigla, String gentilico){
        try {
            Token tokenEntity = tokenRepository.findByToken(token).get();
            if (tokenEntity.isExpired() || !tokenEntity.isAdministrador()) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
            } else if(id != null) {
                return repository.save(
                        Pais.builder()
                                .id(id)
                                .nome(nome)
                                .sigla(sigla)
                                .gentilico(gentilico)
                                .build()
                );
            } else {
                return repository.save(
                        Pais.builder()
                                .nome(nome)
                                .sigla(sigla)
                                .gentilico(gentilico)
                                .build()
                );
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    public List<Pais> listarPaises(String token) {
        try {
            if (tokenRepository.findByToken(token).get().isExpired()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            } else {
                return repository.findAll();
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    public List<Pais> pesquisarPaises(String token, String nome) {
        try {
            if (tokenRepository.findByToken(token).get().isExpired()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            } else {
                return repository.findByNome(nome);
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    public boolean excluirPais(String token, Long id) {
        try {
            Token tokenEntity = tokenRepository.findByToken(token).get();
            if (tokenEntity.isExpired() || !tokenEntity.isAdministrador()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            } else {
                repository.deleteById(id);

                return repository.findById(id).isEmpty();
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
