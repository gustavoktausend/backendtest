package com.gustavokring.backendtest.repository;

import com.gustavokring.backendtest.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {

    @Query("SELECT obj FROM Pais obj WHERE lower(obj.nome) LIKE lower(concat('%', :nome, '%'))")
    List<Pais>findByNome(String nome);
}
