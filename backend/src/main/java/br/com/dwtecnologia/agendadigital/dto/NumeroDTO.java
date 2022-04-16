package br.com.dwtecnologia.agendadigital.dto;

import br.com.dwtecnologia.agendadigital.entities.Numero;

public class NumeroDTO {

	private Long id;
	private String numero;

	public NumeroDTO() {

	}

	public NumeroDTO(Long id, String numero) {
		super();
		this.id = id;
		this.numero = numero;
	}

	public NumeroDTO(Numero numero) {
		this.id = numero.getId();
		this.numero = numero.getNumero();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}