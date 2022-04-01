package br.com.dwtecnologia.agendadigital.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dwtecnologia.agendadigital.entities.Contato;
import br.com.dwtecnologia.agendadigital.repositories.ContatoRepository;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	@Autowired
	private ContatoRepository repositorio;

	@PostMapping("/registrarContato")
	public Contato registrarContato(@Valid @RequestBody Contato contato) {
		repositorio.save(contato);
		return contato;
	}
	
	@GetMapping("/consultarContato")
	public List<Contato> consultarContato() {
		List<Contato> contatos = repositorio.findAll();
		return contatos;
	}

	@DeleteMapping("/removerContato/{id}")
	public void removerContato(@PathVariable Long id) {
		repositorio.deleteById(id);
	}
	
	@PutMapping("/atualizarContato")
	public Contato atualizarContato(Contato contato) {
		repositorio.save(contato);
		return contato;
	}
}
