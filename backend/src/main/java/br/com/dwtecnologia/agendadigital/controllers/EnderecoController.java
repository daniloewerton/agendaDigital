package br.com.dwtecnologia.agendadigital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dwtecnologia.agendadigital.entities.Endereco;
import br.com.dwtecnologia.agendadigital.repositories.EnderecoRepository;

@RestController
@RequestMapping(path = "/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository repositorio;

	@PostMapping("/registrarEndereco")
	public Endereco registrarEndereco(@RequestBody Endereco endereco) {
		repositorio.save(endereco);
		return endereco;
	}
	
	@PatchMapping("/alterarEndereco")
	public Endereco atualizarEndereco(Endereco endereco) {
		repositorio.save(endereco);
		return endereco;
	}
}
