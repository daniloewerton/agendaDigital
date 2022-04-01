package br.com.dwtecnologia.agendadigital.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dwtecnologia.agendadigital.dto.UsuarioDTO;
import br.com.dwtecnologia.agendadigital.dto.UsuarioInsertDTO;
import br.com.dwtecnologia.agendadigital.entities.Usuario;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.repositories.UsuarioRepository;
import br.com.dwtecnologia.agendadigital.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repositorio;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping(path = "/registrarUsuario")
	public ResponseEntity<UsuarioDTO> registrarUsuario(@Valid @RequestBody UsuarioInsertDTO user) {
		try {
			UsuarioDTO usuario = usuarioService.inserirUsuario(user);
			return ResponseEntity.ok(usuario);
		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@GetMapping("/consultarUsuario")
	public List<UsuarioDTO> consultarUsuarios() {
		List<UsuarioDTO> usuario = usuarioService.listaUsuario();
		return usuario;
	}

	// FALTA ISOLAR NO SERVICE
	
	@PutMapping("/atualizarUsuario")
	public Usuario atualizarUsuario(Usuario usuario) {
		repositorio.save(usuario);
		return usuario;
	}

	@DeleteMapping("/removerUsuario/{id}")
	public void removeUsuario(@PathVariable Long id) {
		repositorio.deleteById(id);
	}
}
