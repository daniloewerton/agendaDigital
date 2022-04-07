package br.com.dwtecnologia.agendadigital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dwtecnologia.agendadigital.entities.Numero;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.services.NumeroService;

@RestController
@RequestMapping("/numero")
public class NumeroController {
	
	@Autowired
	private NumeroService numeroService;

	@GetMapping("/registrarNumero")
	public ResponseEntity<Object> registrarNumero(@RequestBody Numero numero) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(numeroService.registrarNumero(numero));
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operação Inválida!");
		}
	}
	
	@GetMapping("/consultaNumeros")
	public ResponseEntity<Object> listaNumeros(@RequestParam(name="id") Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(numeroService.listaNumeros(id));
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
	
	public ResponseEntity<Object> removerNumero(Numero numero) {
		try {
			numeroService.removerNumero(numero);
			return ResponseEntity.status(HttpStatus.OK).body("Numero excluído!");
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operação Inválida!");
		}
	}
}
