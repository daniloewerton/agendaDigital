package br.com.dwtecnologia.agendadigital.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Object> registrarContato(@Valid @RequestBody Contato contato) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.registrarContato(contato));
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
	
	/**
	 * Retorna os contatos cadastrados por determinado usuário.
	 * @param id_usuario Deverá ser informado o ID do usuário qual deseja-se retornar os contatos.
	 * @return Retorna os contatos de um dado usuário, caso exista.
	 */
	@GetMapping("/consultarContato")
	public ResponseEntity<Object> consultarContato(@RequestParam(name = "id_usuario") Long id_usuario) {
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(contatoService.consultarContatos(id_usuario));
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}

	@PutMapping("/atualizarContato/{id}")
	public ResponseEntity<Object> atualizarContato(@RequestBody Contato contato, @PathVariable Long id) {
		try {
			ContatoDTO cont = contatoService.atualizarContato(contato);
			return ResponseEntity.status(HttpStatus.OK).body(cont);
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}

	@DeleteMapping("/removerContato/{id}")
	public ResponseEntity<Object> removerContato(@PathVariable Long id) {
		try {
			contatoService.removerContato(id);
			return ResponseEntity.status(HttpStatus.OK).body("Contato removido!");
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
}
