package com.gustavokring.backendtest.resource;

import com.gustavokring.backendtest.entities.Pais;
import com.gustavokring.backendtest.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pais")
public class PaisResource {

    @Autowired
    private PaisService service;

    @GetMapping("/listar")
    public List<Pais> listarPaises(@RequestParam String token) {
        return service.listarPaises(token);
    }

    @PostMapping("/salvar")
    public Pais inserirPais(@RequestParam String token, @RequestBody Pais pais) {
        return service.insertPais(token, pais.getId(), pais.getNome(),pais.getSigla(), pais.getGentilico());
    }

    @GetMapping("/pesquisar")
    public List<Pais> pesquisarPaises(@RequestParam String token, @RequestParam String filtro) {
        return service.pesquisarPaises(token, filtro);
    }

    @GetMapping("/excluir")
    public boolean excluirPais(@RequestParam String token, @RequestParam Long id) {
        return service.excluirPais(token, id);
    }


}
