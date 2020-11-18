package com.gustavokring.backendtest.resource;

import com.gustavokring.backendtest.dto.UsuarioAutenticado;
import com.gustavokring.backendtest.service.TokenService;
import com.gustavokring.backendtest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/autenticar")
    public UsuarioAutenticado autenticarUsuario(@RequestParam String login, @RequestParam String senha) {
        return service.autenticateUser(login, senha);
    }

    @GetMapping("/renovar-ticket")
    public boolean renovarTicket(@RequestParam String token) {
        return tokenService.reautenticarToken(token);
    }

}
