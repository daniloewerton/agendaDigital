package br.com.dwtecnologia.agendadigital.controllers;

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

import br.com.dwtecnologia.agendadigital.dto.NumeroDTO;
import br.com.dwtecnologia.agendadigital.dto.NumeroInsertDTO;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.services.NumeroService;

@RestController
@RequestMapping("/numero")
public class NumeroController {
	
	@Autowired
	private NumeroService numeroService;

	@PostMapping("/registrarNumero/{id}")
	public ResponseEntity<Object> registrarNumero(@RequestBody NumeroInsertDTO numero, @PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(numeroService.registrarNumero(numero, id));
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
	
	@GetMapping("/consultaNumeros")
	public ResponseEntity<Object> listaNumeros(@RequestParam(name="id") Long id_cont) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(numeroService.listaNumeros(id_cont));
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
	
	@PutMapping("/atualizarNumero/{id_c}")
	public ResponseEntity<Object> atualizarNumero(@RequestBody NumeroDTO numero, @PathVariable Long id_c) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(numeroService.atualizarNumero(numero, id_c));
		} catch (ServiceException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
	
	@DeleteMapping("/removerNumero")
	public ResponseEntity<Object> removerNumero(@RequestParam(name="id") Long id_cont, @RequestParam(name="id_n") Long id_num) {
		try {
			numeroService.removerNumero(id_cont, id_num);
			return ResponseEntity.status(HttpStatus.OK).body("Número removido!");
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
		}
	}
}
