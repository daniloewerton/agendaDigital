package br.com.dwtecnologia.agendadigital.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public abstract class Pessoa {

	@Column(nullable = false)
	@NotBlank
	protected String nome;

	@Column(nullable = false)
	@NotBlank
	protected String sobrenome;

	@Column(nullable = false)
	@NotBlank
	protected String rg;

	@Column(nullable = false)
	@NotBlank
	protected String cpf;

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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}