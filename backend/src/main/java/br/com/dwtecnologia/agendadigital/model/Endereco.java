package br.com.dwtecnologia.agendadigital.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_endereco")
public class Endereco {

	private static int idCount;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String rua;
	private int numero;
	private String complemento;
	private String bairro;
	private int cep;
	private String cidade;
	private String uf;

	public Endereco() {
		idCount++;
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

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUnidadeFederativa() {
		return uf;
	}

	public void setUnidadeFederativa(String unidadeFederativa) {
		this.uf = unidadeFederativa;
	}

	public int getId() {
		return id;
	}

	public void setId() {
		this.id = idCount;
	}
}
