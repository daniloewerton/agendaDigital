package br.com.dwtecnologia.agendadigital.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

	public List<UsuarioDTO> listaUsuario() {
		List<Usuario> list = repositorio.findAll();
		return list.stream().map(u -> new UsuarioDTO(u)).collect(Collectors.toList());
	}

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

	public void removerUsuario(Long id) {
		
		Usuario usuarioBanco = retornaUsuario(id);

		if (usuarioBanco != null) {
			repositorio.delete(usuarioBanco);
		} else {
			throw new ServiceException("Usuário não existente!");
		}
	}

	public Usuario retornaUsuario(Usuario user) {
		Usuario usuario;
		try {
			usuario = repositorio.findById(user.getId()).get();
		} catch (NoSuchElementException exception) {
			usuario = null;
		}
		return usuario;
	}
	
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
