package com.gustavokring.backendtest.repository;

import com.gustavokring.backendtest.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
}
