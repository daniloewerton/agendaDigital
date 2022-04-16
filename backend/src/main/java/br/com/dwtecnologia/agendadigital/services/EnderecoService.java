package br.com.dwtecnologia.agendadigital.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dwtecnologia.agendadigital.dto.EnderecoDTO;
import br.com.dwtecnologia.agendadigital.dto.EnderecoInsertDTO;
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

	/**
	 * Realiza a inserção de um endereço para um dado contato.
	 * 
	 * @param enderecoDto Deverá ser informado o objeto EnderecoDTO desejado.
	 * @param id_contato  Deverá ser informado o ID do contato.
	 * @return Retorna o objeto EnderecoDTO.
	 */
	@Transactional
	public EnderecoDTO inserirEndereco(EnderecoInsertDTO enderecoDto, Long id_contato) {
		Endereco endereco = converterEnderecoInsertDTOemEnderecoDTO(enderecoDto);

		try {
			Optional<Contato> contato = contatoRepositorio.findContatoById(id_contato);
			endereco.setContato(contato.get());
		} catch (Exception exception) {
			throw new ServiceException("Contato não existente!");
		}

		repositorio.save(endereco);
		return new EnderecoDTO(endereco);
	}

	/**
	 * Realiza alteração em um Endereço já existente.
	 * 
	 * @param enderecoDto Deverá ser informado o objeto EnderecoDTO desejado.
	 * @param id_contato  Deverá ser informado o ID do contato que possui tal
	 *                    endereço.
	 * @return Retorna o EnderecoDTO atualizado.
	 */
	@Transactional
	public EnderecoDTO atualizarEndereco(EnderecoDTO enderecoDto, Long id_contato) {

		Optional<Endereco> enderecoBanco = repositorio.procurarEnderecoDeContatoById(enderecoDto.getId(), id_contato);

		try {
			enderecoBanco.get();
		} catch (Exception exception) {
			throw new ServiceException("Endereço ou contato não existente!");
		}

		Endereco enderecoAtualizado = new Endereco();
		enderecoAtualizado.setId(enderecoDto.getId());
		enderecoAtualizado.setRua(enderecoDto.getRua());
		enderecoAtualizado.setBairro(enderecoDto.getBairro());
		enderecoAtualizado.setCidade(enderecoDto.getCidade());
		enderecoAtualizado.setComplemento(enderecoDto.getComplemento());
		enderecoAtualizado.setNumero(enderecoDto.getNumero());
		enderecoAtualizado.setUf(enderecoDto.getUf());
		enderecoAtualizado.setCep(enderecoDto.getCep());
		enderecoAtualizado.setContato(contatoRepositorio.findContatoById(id_contato).get());

		repositorio.save(enderecoAtualizado);

		return new EnderecoDTO(enderecoAtualizado);
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

	/**
	 * Remove um endereço.
	 * 
	 * @param id Deverá ser informado o ID do endereço desejado.
	 */
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
	 * Realiza uma pesquisa no banco de dados e retorna um objeto do tipo Endereço.
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
	 * Realiza uma pesquisa no banco de dados e retorna um objeto do tipo Endereco.
	 * 
	 * @param id Recebe um ID para verificação se o mesmo já existe no banco de
	 *           dados.
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

	/**
	 * Realiza a conversão de um objeto EnderecoInsertDTO em Endereco.
	 * 
	 * @param dto Deverá ser informado o EnderecoInsertDTO desejado.
	 * @return Retorna o objeto Endereco.
	 */
	public Endereco converterEnderecoInsertDTOemEnderecoDTO(EnderecoInsertDTO dto) {
		Endereco endereco = new Endereco();
		endereco.setRua(dto.getRua());
		endereco.setNumero(dto.getNumero());
		endereco.setComplemento(dto.getComplemento());
		endereco.setBairro(dto.getBairro());
		endereco.setCep(dto.getCep());
		endereco.setCidade(dto.getCidade());
		endereco.setUf(dto.getUf());
		return endereco;
	}
}
