package com.gustavokring.backendtest.service;

import com.gustavokring.backendtest.entities.Pais;
import com.gustavokring.backendtest.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {

    @Autowired
    private PaisRepository repository;

    public Pais insertNewPais(String nome, String sigla, String gentilico) {

        return repository.save(
                Pais.builder()
                        .nome(nome)
                        .sigla(sigla)
                        .gentilico(gentilico)
                        .build()
        );
    }
}
