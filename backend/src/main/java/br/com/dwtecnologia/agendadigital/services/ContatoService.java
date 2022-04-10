package br.com.dwtecnologia.agendadigital.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dwtecnologia.agendadigital.dto.ContatoDTO;
import br.com.dwtecnologia.agendadigital.entities.Contato;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.repositories.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository repositorio;

	public ContatoDTO registrarContato(Contato objContato) {

		List<Contato> cont = retornaContato(objContato.getEmail());
		
		for (Contato contato : cont) {
			if (contato == null) {
				repositorio.save(objContato);
			} else if (contato.getUsuario().getId().equals(objContato.getUsuario().getId())) {
				throw new ServiceException("Usuário já possui esse contato!");
			}
		}
		return new ContatoDTO(repositorio.save(objContato));
	}

	public List<ContatoDTO> consultarContatos(Long id) {
		
		try {
			List<Contato> contatos = repositorio.buscarContato(id);
			
			if (!contatos.isEmpty()) {
				return contatos.stream().map(c -> new ContatoDTO(c)).collect(Collectors.toList());
			} else {
				throw new ServiceException("Usuário inexistente!");
			}
		} catch (ServiceException exception) {
			throw new ServiceException("Usuário informado não existe ou não possui contatos cadastrados!");
		}
	}

	public ContatoDTO atualizarContato(Contato contato, Long id) {

		Contato cont = retornaContatoPorUsuario(contato ,id);

		if (cont == null) {
			throw new ServiceException("Contato não existe!");
		} else {

			Contato contatoAtualizado = new Contato();
			contatoAtualizado.setId(contato.getId());
			contatoAtualizado.setNome(contato.getNome());
			contatoAtualizado.setEmail(contato.getEmail());
			contatoAtualizado.setSobrenome(contato.getSobrenome());
			contatoAtualizado.setUsuario(contato.getUsuario());

			ContatoDTO contatodto = new ContatoDTO(contatoAtualizado);

			repositorio.save(contatoAtualizado);

			return contatodto;
		}
	}
	
	@Transactional
	public void removerContato(Long id) {

		Contato contato = retornaContato(id);

		if (contato != null) {
			repositorio.delete(contato);
		} else {
			throw new ServiceException("Contato não existe!");
		}
	}

	public Contato retornaContatoPorUsuario(Contato contato, Long id) {
		Contato contatoBanco;
		try {
			contatoBanco = repositorio.findContatoByUsuarioID(contato.getId(), id);
		} catch (Exception exception) {
			contatoBanco = null;
		}
		return contatoBanco;
	}

	public Contato retornaContato(Long id) {
		Contato contatoBanco;
		try {
			contatoBanco = repositorio.findById(id).get();
		} catch (NoSuchElementException exception) {
			contatoBanco = null;
		}
		return contatoBanco;
	}
	
	public List<Contato> retornaContato(String email) {
		List<Contato> contatoBanco;
		try {
			contatoBanco = repositorio.findByEmail(email);
		} catch (Exception exception) {
			contatoBanco = null;
		}
		return contatoBanco;
	}
}
