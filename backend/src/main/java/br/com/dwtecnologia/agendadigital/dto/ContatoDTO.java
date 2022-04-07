package br.com.dwtecnologia.agendadigital.dto;

import java.util.List;

import br.com.dwtecnologia.agendadigital.entities.Contato;
import br.com.dwtecnologia.agendadigital.entities.Numero;

public class ContatoDTO {

	private Long id;

	private String nome;

	private String sobrenome;

	private String email;

	List<Numero> numeros;

	private UsuarioDTO usuario;

	public ContatoDTO() {

	}

	public ContatoDTO(Contato contato) {
		this.id = contato.getId();
		this.nome = contato.getNome();
		this.sobrenome = contato.getSobrenome();
		this.email = contato.getEmail();
		//this.numeros = contato.getNumeros();
		// this.usuario = contato.getUsuario(); No retorno, não será possível saber a
		// qual usuário os contatos estão vinculados.
	}

	public ContatoDTO(Long id, String nome, String sobrenome, String email, List<Numero> numeros, UsuarioDTO usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.numeros = numeros;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Numero> getNumeros() {
		return numeros;
	}

	public void setNumeros(List<Numero> numeros) {
		this.numeros = numeros;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
}
