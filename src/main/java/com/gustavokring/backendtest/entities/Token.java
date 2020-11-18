package com.gustavokring.backendtest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.Instant;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = Token.TokenBuilder.class)
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

    public boolean isExpired() {
        return Timestamp.from(Instant.now())
                .after(Timestamp.from(expiracao.toInstant().plusSeconds(300)));
    }


    @JsonPOJOBuilder(withPrefix = "")
    public static class TokenBuilder {}
}
