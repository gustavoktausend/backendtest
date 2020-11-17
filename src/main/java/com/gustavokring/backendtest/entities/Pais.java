package com.gustavokring.backendtest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = Pais.PaisBuilder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Pais {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String sigla;
    private String gentilico;



    @JsonPOJOBuilder(withPrefix = "")
    public static class PaisBuilder {}
}
