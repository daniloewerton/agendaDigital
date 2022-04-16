package br.com.dwtecnologia.agendadigital.controllers;

import java.util.List;

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

import br.com.dwtecnologia.agendadigital.dto.EnderecoDTO;
import br.com.dwtecnologia.agendadigital.dto.EnderecoInsertDTO;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.services.EnderecoService;

@RestController
@RequestMapping(path = "/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping("/registrarEndereco/{id}")
	public ResponseEntity<Object> registrarEndereco(@RequestBody EnderecoInsertDTO endereco, @PathVariable("id") Long id_contato) {
		try {
			EnderecoDTO end = enderecoService.inserirEndereco(endereco, id_contato);
			return ResponseEntity.status(HttpStatus.CREATED).body(end);
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}

	@GetMapping("/consultaEndereco/{id}")
	public ResponseEntity<Object> consultarEndereco(@PathVariable Long id) {
		try {
			List<EnderecoDTO> lista = enderecoService.listaEndereco(id);

			if (lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("O contato informado não existe ou não possui endereços cadastrados!");
			}
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

	@PutMapping("/alterarEndereco/{id}")
	public ResponseEntity<Object> atualizarEndereco(@RequestBody EnderecoDTO endereco, @PathVariable(value = "id") Long id_contato) {
		try {
			EnderecoDTO end = enderecoService.atualizarEndereco(endereco, id_contato);
			return ResponseEntity.status(HttpStatus.OK).body(end);
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

	@DeleteMapping("/removerEndereco/{id}")
	public ResponseEntity<Object> removerEndereco(@PathVariable Long id) {
		try {
			enderecoService.removerEndereco(id);
			return ResponseEntity.status(HttpStatus.OK).body("Endereco removido!");
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}
}
