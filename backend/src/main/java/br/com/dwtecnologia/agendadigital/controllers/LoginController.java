package br.com.dwtecnologia.agendadigital.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.dwtecnologia.agendadigital.model.Usuario;
import br.com.dwtecnologia.agendadigital.repositories.UsuarioRepository;

@Controller
@RequestMapping
public class LoginController {

	@Autowired
	UsuarioRepository repositorio;

	@PostMapping("/login")
	public Usuario autenticaUsuario(Usuario usuario) {

		List<Usuario> todosUsuarios = repositorio.findAll();

		for (Usuario user : todosUsuarios) {
			if ((user.getEmail() == usuario.getEmail()) && (user.getSenha() == usuario.getSenha())) {
				return usuario;
			}
		}
		return usuario;
	}
}
