package br.com.dwtecnologia.agendadigital.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dwtecnologia.agendadigital.model.Contato;
import br.com.dwtecnologia.agendadigital.repositories.ContatoRepository;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	
	@Autowired
	private ContatoRepository repositorio;

	@PostMapping("/registrar")
	public Contato registrarContato(@Valid @RequestBody Contato contato) {
		repositorio.save(contato);
		return contato;
	}
}
