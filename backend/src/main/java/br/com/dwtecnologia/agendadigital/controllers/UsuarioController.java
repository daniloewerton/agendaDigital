package br.com.dwtecnologia.agendadigital.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dwtecnologia.agendadigital.model.Usuario;
import br.com.dwtecnologia.agendadigital.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repositorio;

	@PostMapping("/registrar")
	public Usuario registrarUsuario(@Valid @RequestBody Usuario user) {		
		repositorio.save(user);
		return user;
	}
	
	@GetMapping("/recuperar")
	public List<Usuario> consultarUsuarios() {
		List<Usuario> user = repositorio.findAll();
		return user;
	}
}
