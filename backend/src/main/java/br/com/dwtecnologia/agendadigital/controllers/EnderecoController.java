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
import br.com.dwtecnologia.agendadigital.entities.Endereco;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.services.EnderecoService;

@RestController
@RequestMapping(path = "/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping("/registrarEndereco")
	public ResponseEntity<Object> registrarEndereco(@RequestBody Endereco endereco) {
		try {
			Endereco end = enderecoService.inserirEndereco(endereco);
			return ResponseEntity.status(HttpStatus.CREATED).body(end);
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}

	/**
	 * Retorna a lista de endereços de um determinado contato.
	 * 
	 * @param id Deve ser informado o ID do contato para iniciar a busca.
	 * @return Retorna lista de endereços cadastrados para um contato, se existir.
	 */
	@GetMapping("/consultaEndereco/{id}")
	public ResponseEntity<Object> consultarEndereco(@PathVariable Long id) {
		try {
			List<Endereco> lista = enderecoService.listaEndereco(id);

			if (lista.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("O contato informado não possui endereços cadastrados!");
			}
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		}
	}

	@PutMapping("/alterarEndereco")
	public ResponseEntity<Object> atualizarEndereco(@RequestBody Endereco endereco) {
		try {
			EnderecoDTO end = enderecoService.atualizarEndereco(endereco);
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
