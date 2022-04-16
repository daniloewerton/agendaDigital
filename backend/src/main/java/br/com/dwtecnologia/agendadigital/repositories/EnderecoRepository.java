package br.com.dwtecnologia.agendadigital.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.dwtecnologia.agendadigital.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	@Query(value = " SELECT E from Endereco as E where id = ?1 and contato_id = ?2 ")
	public Optional<Endereco> procurarEnderecoDeContatoById(Long id_endereco, Long id_contato);
}
