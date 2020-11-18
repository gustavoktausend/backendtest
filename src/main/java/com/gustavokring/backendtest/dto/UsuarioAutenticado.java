package com.gustavokring.backendtest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = UsuarioAutenticado.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioAutenticado {

    private String login;
    private String nome;
    private String token;
    private boolean administrador;
    private boolean autenticado;

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {}
}
