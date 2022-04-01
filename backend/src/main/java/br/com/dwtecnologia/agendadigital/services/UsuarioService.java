package br.com.dwtecnologia.agendadigital.services;

import java.util.List;
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
		
		Usuario usuario = repositorio.findByEmail(dto.getEmail());
		
		if(usuario != null ) {
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
}
