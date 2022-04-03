package br.com.dwtecnologia.agendadigital.dto;

import br.com.dwtecnologia.agendadigital.entities.Usuario;

public class UsuarioInsertDTO {

	private String nome;

	private String sobrenome;

	private String email;
	
	private String senha;
	
	public UsuarioInsertDTO(Usuario usuario) {
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.email = usuario.getEmail();
		this.email = usuario.getSenha();
	}
	
	public UsuarioInsertDTO(String nome, String sobrenome, String email, String senha) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
