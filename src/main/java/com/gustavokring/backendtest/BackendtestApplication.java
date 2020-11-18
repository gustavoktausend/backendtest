package com.gustavokring.backendtest;

import com.gustavokring.backendtest.service.PaisService;
import com.gustavokring.backendtest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendtestApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PaisService paisService;

	public static void main(String[] args) {
		SpringApplication.run(BackendtestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		usuarioService.insertNewUsuario("convidado", "manager", "Usuário convidado", false);
		usuarioService.insertNewUsuario("admin","suporte", "Gestor", true);
		paisService.insertPais("Brasil", "BR", "Brasileiro");
		paisService.insertPais("Argentina","AR", "Argentino");
		paisService.insertPais("Alemanha", "AL", "Alemão");
	}
}
