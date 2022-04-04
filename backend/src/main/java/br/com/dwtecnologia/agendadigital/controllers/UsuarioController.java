package br.com.dwtecnologia.agendadigital.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.dwtecnologia.agendadigital.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping(path = "/registrarUsuario")
	public ResponseEntity<Object> registrarUsuario(@Valid @RequestBody UsuarioInsertDTO user) {
		try {
			UsuarioDTO usuario = usuarioService.inserirUsuario(user);
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}

	@GetMapping("/consultarUsuario")
	public ResponseEntity<List<UsuarioDTO>> consultarUsuarios() {
		List<UsuarioDTO> usuarios = usuarioService.listaUsuario();
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}

	@PutMapping("/atualizarUsuario/{id}")
	public ResponseEntity<Object> atualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
		try {
			UsuarioDTO user = usuarioService.atualizarUsuario(usuario);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}

	@DeleteMapping("/removerUsuario/{id}")
	public ResponseEntity<Object> removeUsuario(@PathVariable Long id) {
		try {
			usuarioService.removerUsuario(id);
			return ResponseEntity.status(HttpStatus.OK).body("Usuário removido!");
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
}
