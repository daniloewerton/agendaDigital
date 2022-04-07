package br.com.dwtecnologia.agendadigital.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_numeros")
public class Numero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String numero;
	
	@ManyToOne
	private Contato contato;
	
	public Numero() {
		
	}

	public Numero(Long id, String numero, Contato contato) {
		super();
		this.id = id;
		this.numero = numero;
		this.contato = contato;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
}
