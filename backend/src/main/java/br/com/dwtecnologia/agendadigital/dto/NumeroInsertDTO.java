package br.com.dwtecnologia.agendadigital.dto;

import br.com.dwtecnologia.agendadigital.entities.Contato;

public class NumeroInsertDTO {

	private String numero;
	private Contato contato;

	public NumeroInsertDTO() {

	}

	public NumeroInsertDTO(Long id, String numero, Contato contato) {
		super();
		this.numero = numero;
		this.contato = contato;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
}
