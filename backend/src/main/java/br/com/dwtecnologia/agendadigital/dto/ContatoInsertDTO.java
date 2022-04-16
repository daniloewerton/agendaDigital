package br.com.dwtecnologia.agendadigital.dto;

public class ContatoInsertDTO {

	private String nome;

	private String sobrenome;

	private String email;
	
	private Long id_usuario;
	
	public ContatoInsertDTO() {
		
	}

	public ContatoInsertDTO(String nome, String sobrenome, String email, Long id_usuario) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.id_usuario = id_usuario;
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

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
}
