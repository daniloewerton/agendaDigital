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

import br.com.dwtecnologia.agendadigital.dto.ContatoDTO;
import br.com.dwtecnologia.agendadigital.entities.Contato;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.services.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	@Autowired
	private ContatoService contatoService;

	@PostMapping("/registrarContato")
	public ResponseEntity<Contato> registrarContato(@Valid @RequestBody Contato contato) {
		try {
			contatoService.registrarContato(contato);
			return ResponseEntity.ok(contato);
		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@GetMapping("/consultarContato")
	public List<ContatoDTO> consultarContato() {
		List<ContatoDTO> contatos = contatoService.consultarContatos();
		return contatos;
	}

	@PutMapping("/atualizarContato")
	public ResponseEntity<ContatoDTO> atualizarContato(Contato contato) {
		try {
			ContatoDTO cont = contatoService.atualizarContato(contato);
			return ResponseEntity.ok(cont);
		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

	@DeleteMapping("/removerContato/{id}")
	public void removerContato(@PathVariable Long id) {
		contatoService.removerContato(id);
	}
}
