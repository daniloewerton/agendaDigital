package br.com.dwtecnologia.agendadigital.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dwtecnologia.agendadigital.dto.UsuarioDTO;
import br.com.dwtecnologia.agendadigital.dto.UsuarioInsertDTO;
import br.com.dwtecnologia.agendadigital.entities.Usuario;
import br.com.dwtecnologia.agendadigital.exception.ServiceException;
import br.com.dwtecnologia.agendadigital.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositorio;

	/**
	 * Realiza autenticação de um usário, se existir.
	 * 
	 * @param user Deverá ser informado o objeto UsuárioInsertDTO.
	 * @return Retorna o objeto UsuarioInsertDTO.
	 */
	public UsuarioInsertDTO autenticar(UsuarioInsertDTO user) {
		Usuario usuario = retornaUsuario(user.getEmail());

		if (usuario == null) {
			throw new ServiceException("Usuário não encontrado para e-mail informado!");
		}

		if (!usuario.getSenha().equals(user.getSenha())) {
			throw new ServiceException("Senha incorreta!");
		}
		return new UsuarioInsertDTO(usuario);
	}

	/**
	 * Insere um novo usário no sistema.
	 * 
	 * @param dto Deverá ser informado objeto referente ao usuário.
	 * @return Retorna o objeto usuário solicitado. O e-mail não poderá ser
	 *         duplicado.
	 */
	public UsuarioDTO inserirUsuario(UsuarioInsertDTO dto) {

		Usuario usuarioBanco = repositorio.findByEmail(dto.getEmail());

		if (usuarioBanco != null) {
			throw new ServiceException("Email já cadastrado!");
		}

		Usuario obj = new Usuario();
		obj.setNome(dto.getNome());
		obj.setSobrenome(dto.getSobrenome());
		obj.setEmail(dto.getEmail());
		obj.setSenha(dto.getSenha());

		repositorio.save(obj);

		return new UsuarioDTO(obj);
	}

	/**
	 * Retorna uma lista com todos os usuário cadastrados no sistema.
	 * 
	 * @return
	 */
	public List<UsuarioDTO> listaUsuario() {
		List<Usuario> usuarios = repositorio.findAll();
		return usuarios.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList());
	}

	/**
	 * Atualiza as informações totais, ou parciais de um dado usuário.
	 * 
	 * @param usuario Deverá ser informado o objeto referente ao usuário a ser
	 *                alterado.
	 * @return Retorna o objeto usuário com as devidas alterações realizadas.
	 */
	public UsuarioDTO atualizarUsuario(Usuario usuario) {

		Usuario usuarioBanco = retornaUsuario(usuario);

		if (usuarioBanco == null) {
			throw new ServiceException("Usuário não existente!");
		}

		repositorio.save(usuario);

		UsuarioDTO userDTO = new UsuarioDTO();
		userDTO.setNome(usuario.getNome());
		userDTO.setSobrenome(usuario.getSobrenome());
		userDTO.setEmail(usuario.getEmail());
		userDTO.setId(usuario.getId());

		return userDTO;
	}

	/**
	 * Remove um usuário do sistema.
	 * 
	 * @param id Deverá ser informado o ID do usuário.
	 */
	@Transactional
	public void removerUsuario(Long id) {

		Usuario usuarioBanco = retornaUsuario(id);

		if (usuarioBanco != null) {
			repositorio.delete(usuarioBanco);
		} else {
			throw new ServiceException("Usuário não existente!");
		}
	}

	/**
	 * Realiza uma pesquisa no banco de dados e retorna um determinado usuário a
	 * partir de seu e-mail.
	 * 
	 * @param email Deverá ser informado o email do usuaário desejado.
	 * @return Retorna o objeto usuario, se existir.
	 */
	public Usuario retornaUsuario(String email) {
		Usuario usuario;
		try {
			usuario = repositorio.findByEmail(email);
		} catch (NoSuchElementException exception) {
			usuario = null;
		}
		return usuario;
	}

	/**
	 * Realiza uma pesquisa no banco de dados e retorna um determinado usuário.
	 * 
	 * @param user Deverá ser informado a instância do usuário.
	 * @return Retorna o usuário, caso exista.
	 */
	public Usuario retornaUsuario(Usuario user) {
		Usuario usuario;
		try {
			usuario = repositorio.findById(user.getId()).get();
		} catch (NoSuchElementException exception) {
			usuario = null;
		}
		return usuario;
	}

	/**
	 * Realiza uma pesquisa no banco de dados através do ID, e retorna um
	 * determinado usuário.
	 * 
	 * @param id Deverá ser informado o ID do usuário.
	 * @return Retorna o usuário, caso exista.
	 */
	public Usuario retornaUsuario(Long id) {
		Usuario usuario;
		try {
			usuario = repositorio.findById(id).get();
		} catch (NoSuchElementException exception) {
			usuario = null;
		}
		return usuario;
	}
}
