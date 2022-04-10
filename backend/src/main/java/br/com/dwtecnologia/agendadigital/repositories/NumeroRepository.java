package br.com.dwtecnologia.agendadigital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.dwtecnologia.agendadigital.entities.Numero;

public interface NumeroRepository extends JpaRepository<Numero, Long>{

	public List<Numero> findAllById(Long id);
	
	@Query(value = " SELECT N FROM Numero as N WHERE contato_id = ?1 ")
	public List<Numero> buscarNumeroPorContato(Long id_contato);
	
	@Query(value = " SELECT N FROM Numero as N WHERE contato_id = ?1 and id = ?2 ")
	public Numero findNumeroByContato(Long id_contato, Long id_num);
}
