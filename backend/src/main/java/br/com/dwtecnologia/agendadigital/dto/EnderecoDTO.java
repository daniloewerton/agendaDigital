package br.com.dwtecnologia.agendadigital.dto;

import br.com.dwtecnologia.agendadigital.entities.Endereco;

public class EnderecoDTO {

	private Long id;
	private String rua;
	private int numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;

	public EnderecoDTO() {

	}

	public EnderecoDTO(Long id, String rua, int numero, String complemento, String bairro, String cep, String cidade,
			String uf) {
		super();
		this.id = id;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.uf = uf;
	}

	public EnderecoDTO(Endereco end) {
		this.id = end.getId();
		this.rua = end.getRua();
		this.numero = end.getNumero();
		this.complemento = end.getComplemento();
		this.bairro = end.getBairro();
		this.cep = end.getCep();
		this.cidade = end.getCidade();
		this.uf = end.getUf();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
