package br.com.dwtecnologia.agendadigital.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dwtecnologia.agendadigital.dto.NumeroDTO;
import br.com.dwtecnologia.agendadigital.entities.Numero;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.repositories.NumeroRepository;

@Service
public class NumeroService {
	
	@Autowired
	private NumeroRepository repositorio;

	public Numero registrarNumero(Numero numero) {
		repositorio.save(numero);
		return numero;
	}
	
	public List<NumeroDTO> listaNumeros(Long id) {
		
		List<Numero> numeros = new ArrayList<>();
		
		try {
			numeros = repositorio.buscarNumeroPorContato(id);
			
			if (!numeros.isEmpty()) {
				return numeros.stream().map(n -> new NumeroDTO(n)).collect(Collectors.toList());
			} else {
				throw new ServiceException("Nenhum número cadastrado!");
			}
		} catch (Exception exception) {
			throw new ServiceException("Nenhum número cadastrado!");
		}
	}
	
	@Transactional
	public void removerNumero(Numero numero) {
		repositorio.delete(numero);
	}
}
