package br.com.dwtecnologia.agendadigital.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dwtecnologia.agendadigital.dto.NumeroDTO;
import br.com.dwtecnologia.agendadigital.dto.NumeroInsertDTO;
import br.com.dwtecnologia.agendadigital.entities.Numero;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.repositories.ContatoRepository;
import br.com.dwtecnologia.agendadigital.repositories.NumeroRepository;

@Service
public class NumeroService {

	@Autowired
	private NumeroRepository repositorio;

	@Autowired
	private ContatoRepository contatoRepositorio;

	/**
	 * Registra um novo número para um dado contato, caso esse número não exista.
	 * 
	 * @param numero  Deve ser informado o número que se deseja registrar.
	 * @param id_cont Deve ser informado o ID do contato que receberá a inserção do
	 *                novo número.
	 * @return Retorna o número cadastrado, caso esse não exista.
	 */
	@Transactional
	public NumeroDTO registrarNumero(NumeroInsertDTO numeroDto, Long id_contato) {

		Numero numeroSalvar = new Numero();
		List<Numero> numeros = retornaNumeros(id_contato);

		if (numeros.isEmpty()) {
			numeroSalvar.setNumero(numeroDto.getNumero());
			numeroSalvar.setContato(contatoRepositorio.findContatoById(id_contato).get());
			return new NumeroDTO(repositorio.save(numeroSalvar));
		}

		for (Numero numeroAnalisar : numeros) {
			if (numeroAnalisar.getNumero().equals(numeroDto.getNumero())) {
				throw new ServiceException("Contato já possui o número informado!");
			}
		}
		numeroSalvar.setNumero(numeroDto.getNumero());
		numeroSalvar.setContato(contatoRepositorio.findContatoById(id_contato).get());
		return new NumeroDTO(repositorio.save(numeroSalvar));
	}

	/**
	 * Realiza uma pesquisa por números cadastrados para um contato informado.
	 * 
	 * @param id Deve ser informado o ID do contato.
	 * @return Retorna uma lista de números cadastrados para um contato, se existir.
	 */
	public List<NumeroDTO> listaNumeros(Long id_contato) {

		List<Numero> numeros = retornaNumeros(id_contato);

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

	/**
	 * Realiza a atualização em um número existente.
	 * 
	 * @param numeroDto Deverá ser informado o objeto NumeroDTO.
	 * @param id_cont   Deverá ser informado o ID do contato.
	 * @return Retorna o objeto NumeroDTO.
	 */
	public NumeroDTO atualizarNumero(NumeroDTO numeroDto, Long id_cont) {

		Numero numeroBanco = repositorio.findNumeroByContato(id_cont, numeroDto.getId());

		if (numeroBanco != null) {
			Numero numeroAtualizado = new Numero();
			numeroAtualizado.setId(numeroDto.getId());
			numeroAtualizado.setNumero(numeroDto.getNumero());
			numeroAtualizado.setContato(contatoRepositorio.findContatoById(id_cont).get());
			return new NumeroDTO(repositorio.save(numeroAtualizado));
		} else {
			throw new ServiceException("Número ou contato inexistente!");
		}
	}

	/**
	 * Remove um número de um dado contato.
	 * 
	 * @param id_cont Deverá ser informado o ID do contato.
	 * @param id_num  Deverá ser informado o ID do número a ser removido.
	 */
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

	/**
	 * Realiza uma pesquisa no banco de dados e retorna uma lista de números de um
	 * dado contato.
	 * 
	 * @param id_contato Deverá ser informado o ID do contato para efetuar a
	 *                   pesquisa.
	 * @return Retorna a lista de números de um contato, se existir.
	 */
	public List<Numero> retornaNumeros(Long id_contato) {
		List<Numero> numerosNoBanco = new ArrayList<>();
		try {
			numerosNoBanco = repositorio.findNumeroByContato(id_contato);
		} catch (Exception exception) {
			numerosNoBanco = null;
		}
		return numerosNoBanco;
	}

	/**
	 * Realiza uma pesquisa no banco de dados e retorna um único número de um dado
	 * contato.
	 * 
	 * @param id_contato Deverá ser informado o ID do contato para efetuar a
	 *                   pesquisa.
	 * @param id_num     Deverá ser informado o ID do número para efetuar a
	 *                   pesquisa.
	 * @return Retorna o número solicitado, se existir.
	 */
	public Numero retornaNumeros(Long id_contato, Long id_num) {
		Numero numeroNoBanco = repositorio.findNumeroByContato(id_contato, id_num);
		return numeroNoBanco;
	}
}
