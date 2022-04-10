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

	/**
	 * Registra um novo número para um dado contato, caso esse número não exista.
	 * 
	 * @param numero  Deve ser informado o número que se deseja registrar.
	 * @param id_cont Deve ser informado o ID do contato que receberá a inserção do
	 *                novo número.
	 * @return Retorna o número cadastrado, caso esse não exista.
	 */
	public NumeroDTO registrarNumero(Numero numero, Long id_cont) {
		List<Numero> numeros = new ArrayList<>();
		try {
			numeros = retornaNumeros(id_cont);

			for (Numero numeroAnalisar : numeros) {
				if (numeroAnalisar.getNumero().equals(numero.getNumero())) {
					throw new ServiceException("Contato já possui o número informado!");
				} else {
					repositorio.save(numero);
				}
			}
		} catch (ServiceException exception) {
			throw new ServiceException("Contato já possui o número informado!");
		}
		return new NumeroDTO(numero);
	}

	/**
	 * Realiza uma pesquisa por números cadastrados para um contato informado.
	 * 
	 * @param id Deve ser informado o ID do contato.
	 * @return Retorna uma lista de números cadastrados para um contato, se existir.
	 */
	public List<NumeroDTO> listaNumeros(Long id_contato) {

		List<Numero> numeros = new ArrayList<>();

		try {
			numeros = retornaNumeros(id_contato);

			if (!numeros.isEmpty()) {
				return numeros.stream().map(n -> new NumeroDTO(n)).collect(Collectors.toList());
			} else {
				throw new ServiceException("Contato não existe ou não possui números cadastrados!");
			}
		} catch (Exception exception) {
			throw new ServiceException("Contato não existe ou não possui números cadastrados!");
		}
	}

	@Transactional
	public void removerNumero(Long id_cont, Long id_num) {
		
		Numero numeroArmazenado = retornaNumeros(id_cont, id_num);
		
		try {
			if (numeroArmazenado.getId() == id_num) {
				repositorio.deleteById(id_num);
			}
		} catch (Exception exception) {
			throw new ServiceException("Número inexistente!");
		}
	}

	public List<Numero> retornaNumeros(Long id_contato) {
		List<Numero> numerosNoBanco = repositorio.buscarNumeroPorContato(id_contato);
		return numerosNoBanco;
	}

	public Numero retornaNumeros(Long id_contato, Long id_num) {
		Numero numeroNoBanco = repositorio.findNumeroByContato(id_contato, id_num);
		return numeroNoBanco;
	}
}
