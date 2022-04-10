package br.com.dwtecnologia.agendadigital.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dwtecnologia.agendadigital.dto.EnderecoDTO;
import br.com.dwtecnologia.agendadigital.entities.Contato;
import br.com.dwtecnologia.agendadigital.entities.Endereco;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.repositories.ContatoRepository;
import br.com.dwtecnologia.agendadigital.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repositorio;

	@Autowired
	private ContatoRepository contatoRepositorio;

	public EnderecoDTO inserirEndereco(Endereco endereco) {
		repositorio.save(endereco);
		return new EnderecoDTO(endereco);
	}

	public EnderecoDTO atualizarEndereco(Endereco endereco) {

		Endereco enderecoBanco = retornaEndereco(endereco);

		if (enderecoBanco == null) {
			throw new ServiceException("Endereço não existente!");
		} else {

			Endereco enderecoAtualizado = new Endereco();
			enderecoAtualizado.setId(endereco.getId());
			enderecoAtualizado.setRua(endereco.getRua());
			enderecoAtualizado.setBairro(endereco.getBairro());
			enderecoAtualizado.setCidade(endereco.getCidade());
			enderecoAtualizado.setComplemento(endereco.getComplemento());
			enderecoAtualizado.setNumero(endereco.getNumero());
			enderecoAtualizado.setUf(endereco.getUf());
			enderecoAtualizado.setCep(endereco.getCep());
			enderecoAtualizado.setContato(endereco.getContato());

			repositorio.save(enderecoAtualizado);

			EnderecoDTO end = new EnderecoDTO(enderecoAtualizado);

			return end;
		}
	}

	/**
	 * Retorna a lista de endereços de um determinado contato.
	 * 
	 * @param id Deve ser informado o ID do contato para iniciar a busca.
	 * @return Retorna lista de endereços cadastrados para um contato, se existir.
	 */
	public List<EnderecoDTO> listaEndereco(Long id) {

		Contato contato = null;

		try {
			contato = contatoRepositorio.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new ServiceException("Foi informado um contato não existente!");
		}

		List<Endereco> listaBanco = repositorio.findAll();
		List<Endereco> enderecoDoContato = new ArrayList<>();

		for (Endereco lista : listaBanco) {
			if (lista.getContato().getId() == contato.getId()) {
				enderecoDoContato.add(lista);
			}
		}
		return enderecoDoContato.stream().map(e -> new EnderecoDTO(e)).collect(Collectors.toList());
	}

	@Transactional
	public void removerEndereco(Long id) {
		Endereco end = retornaEndereco(id);
		
		if (end != null) {
			repositorio.delete(end);
		} else {
			throw new ServiceException("Endereço não encontrado!");
		}
	}

	/**
	 * Retorna um objeto do tipo Endereço.
	 * 
	 * @param endereco Recebe um endereço para verificação se o mesmo já existe no
	 *                 banco de dados.
	 * @return Retorna o endereço, caso encontrado.
	 */
	public Endereco retornaEndereco(Endereco endereco) {
		Endereco end;
		try {
			end = repositorio.findById(endereco.getId()).get();
		} catch (ServiceException | NoSuchElementException exception) {
			throw new ServiceException("Endereço não existente!");
		}
		return end;
	}

	/**
	 * Retorna um objeto do tipo Endereco.
	 * @param id Recebe um ID para verificação se o mesmo já existe no banco de dados.
	 * @return Retorna o endereço, caso encontrado.
	 */
	public Endereco retornaEndereco(Long id) {
		Endereco end = null;
		try {
			end = repositorio.findById(id).get();
		} catch (NoSuchElementException exception) {
			throw new ServiceException("Endereço não existente!");
		}
		return end;
	}
}
