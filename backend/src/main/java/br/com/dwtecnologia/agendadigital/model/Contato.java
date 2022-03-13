package br.com.dwtecnologia.agendadigital.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_contatos")
public class Contato extends Pessoa {

	private static Long idCount = 0L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Usuario usuario;

	@OneToMany
	@JoinColumn
	List<Endereco> enderecoContato = new ArrayList<>();

	public Contato() {
		idCount++;
	}

	public Contato(String nome, String sobrenome, String rg, String cpf, List<Endereco> endereco) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.rg = rg;
		this.cpf = cpf;
		this.enderecoContato = endereco;
	}

	public Long getId() {
		return this.id;
	}

	public void setId() {
		this.id = idCount;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Endereco> getEndereco() {
		return enderecoContato;
	}

	public void setEndereco(Endereco endereco) {
		this.enderecoContato.add(endereco);
	}

	public List<Endereco> getEnderecoContato() {
		return enderecoContato;
	}

	public void setEnderecoContato(List<Endereco> enderecoContato) {
		this.enderecoContato = enderecoContato;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}