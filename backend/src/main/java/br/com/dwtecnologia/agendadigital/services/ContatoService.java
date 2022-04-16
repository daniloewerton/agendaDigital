package br.com.dwtecnologia.agendadigital.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dwtecnologia.agendadigital.dto.ContatoDTO;
import br.com.dwtecnologia.agendadigital.dto.ContatoInsertDTO;
import br.com.dwtecnologia.agendadigital.entities.Contato;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.repositories.ContatoRepository;
import br.com.dwtecnologia.agendadigital.repositories.UsuarioRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repositorio;

	@Autowired
	private UsuarioRepository repositorioUsuario;

	/**
	 * Insere um novo contato para um dado usuário.
	 * 
	 * @param objContato Deverá ser informado o objeto ContatoInsertDTO desejado.
	 * @return
	 */
	@Transactional
	public ContatoDTO registrarContato(ContatoInsertDTO objContato) {

		List<Contato> cont = retornaContato(objContato.getEmail());

		for (Contato contato : cont) {
			if (contato == null) {
				return new ContatoDTO(repositorio.save(converterContatoInsertDTOemContato(objContato)));
			}

			if (contato.getUsuario().getId().equals(objContato.getId_usuario())) {
				throw new ServiceException("Usuário já possui esse contato!");
			}

			if (repositorioUsuario.findUsuarioById(objContato.getId_usuario()) == null) {
				throw new ServiceException("Usuário inexistitente!");
			}
		}
		return new ContatoDTO(repositorio.save(converterContatoInsertDTOemContato(objContato)));
	}

	/**
	 * Retorna os contatos de um dado usuário.
	 * 
	 * @param id_usu Deverá ser informado o ID do usuário desejado.
	 * @return Retorna uma lista de contatos, se existir.
	 */
	public List<ContatoDTO> consultarContatos(Long id_usu) {

		try {
			List<Contato> contatos = repositorio.findContatoByIDUsuario(id_usu);

			if (!contatos.isEmpty()) {
				return contatos.stream().map(c -> new ContatoDTO(c)).collect(Collectors.toList());
			} else {
				throw new ServiceException("Usuário inexistente!");
			}
		} catch (ServiceException exception) {
			throw new ServiceException("Usuário informado não existe ou não possui contatos cadastrados!");
		}
	}

	/**
	 * Realiza atualização total ou parcial de um contato.
	 * 
	 * @param contato Deverá ser informada a instância do objeto contato.
	 * @param id      Deverá ser informado o ID do contato.
	 * @return Retorna o objeto contato atualizado.
	 */
	@Transactional
	public ContatoDTO atualizarContato(ContatoDTO contatoDto, Long id) {

		Contato cont = retornaContatoPorUsuario(contatoDto, id);

		if (cont == null) {
			throw new ServiceException("Contato ou usuário inexistentes!");
		} else {
			Contato contato = converterContatoDTOemContato(contatoDto);
			contato.setUsuario(repositorioUsuario.findUsuarioById(id));
			repositorio.save(contato);
			return new ContatoDTO(contato);
		}
	}

	/**
	 * Remove um contato.
	 * 
	 * @param id Deverá ser informado o ID do contato desejado.
	 */
	@Transactional
	public void removerContato(Long id) {

		Contato contato = retornaContato(id);

		if (contato != null) {
			repositorio.delete(contato);
		} else {
			throw new ServiceException("Contato não existe!");
		}
	}

	/**
	 * Realiza uma pesquisa no banco de dados e retorna um contato de um dado
	 * usuário.
	 * 
	 * @param contato Deverá ser informada a instância do objeto contato.
	 * @param id_usu  Deverá ser informado o ID do usuário desejado.
	 * @return Retorna o contato, se existir.
	 */
	public Contato retornaContatoPorUsuario(ContatoDTO contato, Long id_usu) {
		Contato contatoBanco;
		try {
			contatoBanco = repositorio.findContatoByUsuarioID(id_usu, contato.getId());
		} catch (Exception exception) {
			contatoBanco = null;
		}
		return contatoBanco;
	}

	/**
	 * Realiza uma pesquisa no banco de dados e retorna um contato.
	 * 
	 * @param email Deverá ser informado o ID do contato desejado.
	 * @return Retorna o contato, se existir.
	 */
	public Contato retornaContato(Long id) {
		Contato contatoBanco;
		try {
			contatoBanco = repositorio.findById(id).get();
		} catch (NoSuchElementException exception) {
			contatoBanco = null;
		}
		return contatoBanco;
	}

	/**
	 * Realiza uma pesquisa no banco de dados e retorna um contato.
	 * 
	 * @param email Deverá ser informado o e-mail do contato desejado.
	 * @return Retorna o contato, se existir.
	 */
	public List<Contato> retornaContato(String email) {
		List<Contato> contatoBanco;
		try {
			contatoBanco = repositorio.findByEmail(email);
		} catch (Exception exception) {
			contatoBanco = null;
		}
		return contatoBanco;
	}

	/**
	 * Realiza a conversão de um objeto ContatoInsertDTO em um objeto Contato.
	 * 
	 * @param dto Deverá ser informado o objeto ContatoInsertDTO para ser
	 *            convertido.
	 * @return Retorna o objeto Contato correspondente.
	 */
	public Contato converterContatoInsertDTOemContato(ContatoInsertDTO dto) {
		Contato contato = new Contato();
		contato.setNome(dto.getNome());
		contato.setSobrenome(dto.getSobrenome());
		contato.setEmail(dto.getEmail());
		contato.setUsuario(repositorioUsuario.findUsuarioById(dto.getId_usuario()));
		return contato;
	}

	/**
	 * Realiza a conversão de um objeto ContatoDTO em um objeto Contato.
	 * 
	 * @param contatoDto Deverá ser informado o objeto ContatoDTO para ser
	 *                   convertido.
	 * @return Retorna o objeto Contato correspondente.
	 */
	public Contato converterContatoDTOemContato(ContatoDTO contatoDto) {
		Contato contato = new Contato();
		contato.setId(contatoDto.getId());
		contato.setNome(contatoDto.getNome());
		contato.setEmail(contatoDto.getEmail());
		contato.setSobrenome(contatoDto.getSobrenome());
		return contato;
	}
}
