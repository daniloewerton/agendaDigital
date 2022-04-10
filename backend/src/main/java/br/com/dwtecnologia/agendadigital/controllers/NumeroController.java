package br.com.dwtecnologia.agendadigital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<Object> registrarNumero(@RequestBody Numero numero, @RequestParam(name="id_cont") Long id_cont) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(numeroService.registrarNumero(numero, id_cont));
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
	
	@GetMapping("/consultaNumeros")
	public ResponseEntity<Object> listaNumeros(@RequestParam(name="id_cont") Long id_cont) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(numeroService.listaNumeros(id_cont));
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
	
	@DeleteMapping("/removerNumero")
	public ResponseEntity<Object> removerNumero(@RequestParam(name="id_cont") Long id_cont, @RequestParam(name="id_n") Long id_num) {
		try {
			numeroService.removerNumero(id_cont, id_num);
			return ResponseEntity.status(HttpStatus.OK).body("Número removido!");
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
}
