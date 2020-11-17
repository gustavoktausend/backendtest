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
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = Token.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Long id;
    private String token;
    private String login;
    private Timestamp expiracao;
    private boolean administrador;


    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {}
}
