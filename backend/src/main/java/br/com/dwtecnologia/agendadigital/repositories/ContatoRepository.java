package br.com.dwtecnologia.agendadigital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.dwtecnologia.agendadigital.entities.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>  {

	@Query(value = " SELECT C FROM Contato as C WHERE usuario_id = ?1 ")
	public List<Contato> buscarContato(Long id);
	
}
